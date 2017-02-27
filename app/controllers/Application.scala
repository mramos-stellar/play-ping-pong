package controllers

import javax.inject.Inject

import dao.OAuthTokenDao
import models.OAuthToken
import play.api._
import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import play.api.inject
import slick.driver.JdbcProfile

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class Application @Inject() (oauthTokensDao: OAuthTokenDao) extends Controller {

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
    result.map(token => Ok(s"oauth result: $token"))
  }
}