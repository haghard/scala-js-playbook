package playbook

import akka.actor.ActorSystem
import com.typesafe.config.{Config, ConfigFactory}
import scala.collection._

//-Duser.timezone=UTC
//TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
object Application extends App with AppSupport {
  val opts: Map[String, String] = argsToOpts(args.toList)
  applySystemProperties(opts)

  val httpPort = System.getProperty("akka.http.port")
  val httpConf =
    s"""
       |akka.http.port=%httpP%
       |akka.http.interface=%interface%
    """.stripMargin

  val config: Config = ConfigFactory.load()

  val system: ActorSystem = ActorSystem("js-playbook", config)
  system.actorOf(HttpServer.props(httpPort.toInt), "http-server")

  scala.io.StdIn.readLine()
  system.terminate()
}