package com.anand.main.service
import org.log4s._
import spray.http.{HttpEntity, HttpResponse}
import spray.routing.RequestContext
import spray.http.MediaTypes._

object ScakkaService {
  private val logger = getLogger

  val isRunning:(RequestContext) =>Unit = (ctx:RequestContext) => {
    ctx.complete(HttpResponse(entity = HttpEntity(`application/json`, "{\"hello\" : \"World\"}")))
  }
}