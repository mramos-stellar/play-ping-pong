package dao


import javax.inject.Inject

import models.OAuthToken
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.db.NamedDatabase
//import slick.lifted._
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import com.github.tototoshi.slick.MySQLJodaSupport._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
  * Created by mramos on 27/02/2017.
  */
class OAuthTokenDao @Inject() (@NamedDatabase("default") protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{

  private val OAuthTokens = TableQuery[OAuthTokensTable]

  def findByToken(token: String): Future[Option[OAuthToken]] =
    db.run(OAuthTokens.filter(_.token === token).result.headOption)


  private class OAuthTokensTable(tag: Tag) extends Table[OAuthToken](tag, "oauth_access_tokens") {

    def id = column[Int]("id", O.PrimaryKey)
    def resourceOwnerId = column[Int]("resource_owner_id",O.PrimaryKey, O.AutoInc)
    def applicationId = column[Option[Int]]("application_id")
    def token = column[String]("token")
    def refreshToken = column[String]("refresh_token")
    def expiresIn = column[Option[Int]]("expires_in")
    def revokedAt = column[Option[DateTime]]("revoked_at")
    def createdAt = column[Option[DateTime]]("created_at")
    def scopes = column[String]("scopes")
    def locationId = column[Option[Int]]("location_id")

    def * = (id,resourceOwnerId,applicationId,token,refreshToken,expiresIn,revokedAt,createdAt,scopes,locationId) <> (OAuthToken.tupled, OAuthToken.unapply)
  }
}
