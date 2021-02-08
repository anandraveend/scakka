package com.anand.main.server

import akka.actor.{Actor, ActorRefFactory, ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import spray.routing.{HttpService, Route}
import org.log4s._
import spray.routing.Directive.pimpApply
import com.anand.main.service.ScakkaWebService
import com.gettyimages.spray.swagger.SwaggerHttpService
import com.wordnik.swagger.model.ApiInfo

import scala.reflect.runtime.universe._
import scala.reflect.runtime.universe

object Server extends App {

    private val logger = getLogger
    implicit val system = ActorSystem("scakka-server")    
    implicit val serviceName = " scakka-service"
    
    val serverConfig= system.settings.config.getConfig(serviceName + ".server")
    val service = system.actorOf(Props[Server], "scakkaService")

    logger.info(s"Starting server ${serviceName}")
    val host=serverConfig.getString("host")
    val port = serverConfig.getInt("port")

    IO(Http) ! Http.Bind(service,interface = host, port = port)

    logger.info(s"Started server at ${host}:${port}")
}


class Server extends Actor with ScakkaServices {
    private val logger = getLogger
    val actorRefFactory = context
    val serverConfig = Server.serverConfig
    val swaggerService = new SwaggerHttpServiceWithPathPrefixSupport {
        override def docPathPrefix: String = (serverConfig.getString("service-path") + "[^/]*")
        override def docsPath: String = "api-docs"
        override def apiTypes: Seq[universe.Type] = Seq(typeOf[ScakkaWebService])
        override def apiVersion: String = "1.0"
        override def baseUrl: String = "/" + serverConfig.getString("service-path")
        override implicit def actorRefFactory: ActorRefFactory = context
        override def apiInfo = Some(new ApiInfo("Anand's Scala AKKA Serice",
            "Same as title","","anand.raveendran@gmail.com",
            "Apache V2",""))
    }
    val allRoutes = pathPrefix(rootPath) { rootPath =>
        commonRoutes ~
        path("browse") { get { getFromResource("swagger/index.html")}} ~
        get { getFromResourceDirectory("swagger") } ~
        reject
    }

    val receive = runRoute(allRoutes ~ swaggerService.routesWithPrefix)
}

trait ScakkaServices extends HttpService with ScakkaWebService{
  implicit def executionContext = actorRefFactory.dispatcher
  val rootPath = (Server.serverConfig.getString("service-path") + "[^/]*").r
//  val mainRoute = pathPrefix(rootPath) { rootPath =>
//    commonRoutes ~
//      path("browse") { get { getFromResource("resources/swagger/index.html")}} ~
//      get { getFromResourceDirectory("resources/swagger") } ~
//      reject
//  }

}

trait SwaggerHttpServiceWithPathPrefixSupport extends SwaggerHttpService {
    def docPathPrefix :String
    val routePrefix = (docPathPrefix + "[^/]*").r

    def routesWithPrefix : Route = pathPrefix(routePrefix) { routePrefix =>
        super.routes
    }
}