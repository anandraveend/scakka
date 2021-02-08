package com.anand.main.actors

import akka.actor.{Actor, Props, actorRef2Scala}
import com.anand.main.service.ScakkaService
import spray.routing.RequestContext

trait ScakkaActorController extends Actor {
  import context._
  def processIsRunning(ctx: RequestContext) = {
    val actor = actorOf(Props[ScakkaActor])
    actor ! ScakkaActor.execProcessIsRunning(ctx)
  }
}

class ScakkaActor extends Actor {
  def receive = {
    case ScakkaActor.execProcessIsRunning(ctx) => {
      ScakkaService.isRunning(ctx)
    }
  }
}

object ScakkaActor {
  case class execProcessIsRunning(ctx: RequestContext)
}