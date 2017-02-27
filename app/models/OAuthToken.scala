package models

import org.joda.time.DateTime

/**
  * Created by mramos on 27/02/2017.
  *   def id = column[Int]("id", O.PrimaryKey)
    def resourceOwnerId = column[Int]("resource_owner_id",O.PrimaryKey, O.AutoInc)
    def applicationId = column[Option[Int]]("application_id")
    def token = column[String]("token")
    def refreshToken = column[String]("refresh_token")
    def expiresIn = column[Option[Int]]("expires_in")
    def revokedAt = column[Option[DateTime]]("revoked_at")
    def createdAt = column[DateTime]("created_at")
    def scopes = column[String]("scopes")
    def locationId = column[Option[Int]]("location_id")
  */
case class OAuthToken (id: Int, resourceOwnerId: Int, applicationId: Option[Int], token: String, refreshToken: String,
                       expiresIn: Option[Int], revokedAt: Option[DateTime], createdAt: Option[DateTime], scopes: String, locationId: Option[Int]) {

}
