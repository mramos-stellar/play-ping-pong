package controllers

import javax.inject.Inject

import dao.{MembersDao, OAuthTokenDao}
import models.OAuthToken
import models.rails.Tables.OauthAccessTokensRow
import play.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import play.api.inject
import slick.driver.JdbcProfile
import models.rails.Tables._
import play.api.libs.json._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject() (oauthTokensDao: OAuthTokenDao, membersDao: MembersDao) extends Controller {
//  implicit val membersRowWrites = Json.writes[MembersRow]

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def ping() = Action {
    Ok("pong")
  }

  def profile() = Action.async { implicit request =>
    val qs = request.getQueryString("access_token").get
    Logger.debug(s"token: $qs")
    val result = oauthTokensDao.findByToken(qs)
    for {
      row <- result.mapTo[Option[OauthAccessTokensRow]]
      memberId = row.get.resourceOwnerId.get
      someMember <- membersDao.findByResourceOwnerId(memberId).mapTo[Option[MembersRow]]
      member = someMember.get
    }yield {
      Ok(member)
    }
  }
}