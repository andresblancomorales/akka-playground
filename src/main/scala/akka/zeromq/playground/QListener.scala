package akka.zeromq.playground

import akka.actor.{Props, ActorSystem, Actor}
import akka.actor.Actor.Receive
import akka.zeromq._

class QListener extends Actor {
  
  val actorContext = ZeroMQExtension(context.system).newSocket(SocketType.Rep, Listener(self), Bind("tcp://*:5555"))
  
  override def receive: Receive = {
    case message : ZMQMessage => println(message.frames(0).utf8String)
    
  }
}
