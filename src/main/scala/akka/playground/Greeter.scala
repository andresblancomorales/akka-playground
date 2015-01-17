package akka.playground

import akka.actor.Actor
import akka.playground.messages.{Salutation, Greeting}

class Greeter extends Actor {
  override def receive: Receive = {
    case Greeting(name) => {
      sender ! Salutation(s"Good morning Mr. ${name}!")
    }
    case _ => println("Greet me or I'll ignore you! ><")
  }
}
