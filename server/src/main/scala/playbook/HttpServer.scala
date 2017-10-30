package playbook

import akka.stream.ActorMaterializerSettings
import akka.actor.{Actor, ActorLogging, Props, Status}

object HttpServer {
  val HttpDispatcher = "akka.http.dispatcher"
  object Stop

  def props(interface: String, port: Int) = Props(new HttpServer(interface, port)).withDispatcher(HttpDispatcher)
}

class HttpServer(interface: String, port: Int) extends Actor with ActorLogging {
  import HttpServer._
  import akka.http.scaladsl.Http
  import akka.pattern.pipe
  import akka.http.scaladsl.server.RouteResult._
  import akka.http.scaladsl.server.RouteConcatenation._

  implicit val system = context.system
  implicit val ex = system.dispatchers.lookup(HttpDispatcher)
  implicit val mat = akka.stream.ActorMaterializer(
    ActorMaterializerSettings.create(system).withDispatcher(HttpDispatcher)
  )(system)

  val routes = new api.EndpointsApi().route

  Http()
    .bindAndHandle(routes, interface, port)
    .pipeTo(self)

  override def receive = {
    case b: akka.http.scaladsl.Http.ServerBinding => serverBinding(b)
    case Status.Failure(c) => handleBindFailure(c)
  }

  def serverBinding(b: akka.http.scaladsl.Http.ServerBinding) = {
    log.info("Binding on {}",  b.localAddress)
    context become bound(b)
  }

  def handleBindFailure(cause: Throwable) = {
    log.error(cause, s"Can't bind to $interface:$port!")
    (context stop self)
  }

  def bound(b: akka.http.scaladsl.Http.ServerBinding): Receive = {
    case HttpServer.Stop =>
      log.info("Unbound {}:{}", interface, port)
      b.unbind().onComplete { _ =>  mat.shutdown }
  }
}