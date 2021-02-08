package com.anand.main.service

import com.wordnik.swagger.annotations.{Api, ApiOperation}
import spray.routing.{HttpService, RequestContext}
import spray.routing.Directive.pimpApply
import org.log4s._
import javax.ws.rs._
import spray.http.MediaTypes._
import com.anand.main.actors._

@Api(value="/common", description="Main Service Endpoint", produces = "application/json", position = 1)
trait ScakkaWebService extends HttpService with ScakkaActorController{
  private val logger = getLogger

  def commonRoutes:RequestContext => Unit = isRunning

  @GET
  @Path("/isRunning")
  @ApiOperation(
    nickname="isRunning",
    value="Check if the services are up",
    httpMethod = "GET",
    produces = "application/json"
  )
  def isRunning = path("common" / "isRunning") {
    logger.info("Recieved request for isRunning service")
    get {
      respondWithMediaType(`application/json`) {
        try {
          requestContext => processIsRunning(requestContext)
        }catch {
          case e:Exception => {
            logger.error(s"Error in isRunning ${e.getMessage}")
            throw e
          }
        }
      }
    }
  }
}