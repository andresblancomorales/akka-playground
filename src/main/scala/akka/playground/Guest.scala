package akka.playground

import akka.actor.{Props, ActorRef, Actor}
import akka.pattern.ask
import akka.playground.messages.{Salutation, Greeting, EnterLobby}
import akka.util.Timeout
import scala.concurrent.duration._

class Guest extends Actor {
  private var host: Option[ActorRef] = None
  private var running : Boolean = false

  override def receive: Receive = {
    case EnterLobby() => {
      if (!running) {
        running = true
        host = Some(sender)

        context.actorOf(Props[Greeter]) ! Greeting("Guest")
      }
    }
    case Salutation(salute) => {
      host.map(_ ! s"Guest\n${salute}")
    }
    case _ => println("You can't enter lobby")
  }
}
