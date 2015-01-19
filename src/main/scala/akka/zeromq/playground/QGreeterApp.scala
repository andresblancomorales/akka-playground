package akka.zeromq.playground

import akka.actor._
import akka.playground.Guest
import akka.playground.messages.EnterLobby
import akka.routing.RoundRobinRouter
import akka.util.{ByteString, Timeout}
import akka.zeromq._
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._

import scala.concurrent.Await


object QGreeterApp {
  

  def main(args: Array[String]): Unit = {    
    val greeterSystem = ActorSystem("QTest")
    val listenerActor = greeterSystem.actorOf(Props[QListener],"ListenerActor")
    val request = ZeroMQExtension(greeterSystem).newReqSocket(Array(SocketType.Req,Connect("tcp://127.0.0.1:5555")))
    Thread.sleep(1000)
    request ! ZMQMessage(ByteString.fromString("did you get this message?"))
    Thread.sleep(2000)
    greeterSystem.shutdown()
  }
}
