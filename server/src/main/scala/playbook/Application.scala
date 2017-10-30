package playbook

import akka.actor.ActorSystem
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection._
import scala.concurrent.Await

//-Duser.timezone=UTC
//TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
object Application extends App with AppSupport {
  val opts: Map[String, String] = argsToOpts(args.toList)
  applySystemProperties(opts)

  /*val httpPort = System.getProperty("akka.http.port")
  val httpConf =
    s"""
       |akka.http.port=%httpP%
       |akka.http.interface=%interface%
    """.stripMargin
  */

  val config: Config = ConfigFactory.load().resolve()

  val interface = config.getString("akka.http.interface")
  val port = config.getInt("akka.http.port")

  val system: ActorSystem = ActorSystem("js-playbook", config)
  val g = system.actorOf(HttpServer.props(interface, port), "http-server")

  sys.addShutdownHook {
    system.stop(g)
    import scala.concurrent.duration._
    Await.result(system.terminate, 5.seconds)
  }

  //scala.io.StdIn.readLine()
  //system.terminate()
}