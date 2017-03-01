package dao

import javax.inject.Inject

import models.OAuthToken
import models.rails.Tables._
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
//import slick.lifted._
import com.github.tototoshi.slick.MySQLJodaSupport._
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

/**
  * Created by mramos on 27/02/2017.
  */
class MembersDao @Inject()(@NamedDatabase("default") protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{


  def findByResourceOwnerId(resourceOwnerId: Int): Future[Option[MembersRow]] =
    db.run(Members.filter(_.id === resourceOwnerId).result.headOption)

}
