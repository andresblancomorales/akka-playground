package akka.playground

import akka.actor.{Props, ActorSystem}
import akka.playground.messages.EnterLobby
import akka.routing.RoundRobinRouter
import akka.util.Timeout
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._

object GreeterApp extends App {

  implicit val ec = global

  override def main(args: Array[String]): Unit = {
    val greeterSystem = ActorSystem("Greeter")
    val actor = greeterSystem.actorOf(Props(new Guest()).withRouter(RoundRobinRouter(50)), name = "workerRouter")

    implicit val timeout = Timeout(25 seconds)
    val future = actor ? EnterLobby()
    val result = Await.result(future, timeout.duration).asInstanceOf[String]
    println(result)
    greeterSystem.shutdown()
  }
}
