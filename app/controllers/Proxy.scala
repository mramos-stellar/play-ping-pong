package controllers

import play.api.Logger
import play.api.libs.ws.WS
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits._
import play.api.Play.current
/**
  * Created by mramos on 24/02/2017.
  */
class Proxy extends Controller{

  def index(url: String) = Action.async { request =>
    val newUrl = s"http://localhost:8080/$url?access_token=${request.getQueryString("access_token").get}"
    Logger.info(newUrl)
//    WS.url(newUrl).withBody(request.body).withQueryString(request.queryString.unzip)
    WS.url(newUrl).withHeaders(("Accept","application/vnd.stellar-v1+json")).get().map(resp => Ok(resp.body).as("application/vnd.stellar-v1+json"))
  }
}
