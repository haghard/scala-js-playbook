package playbook.api

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpEntity.Strict
import akka.http.scaladsl.model.{ContentTypes, HttpResponse}
import akka.http.scaladsl.server._
import akka.util.ByteString
import playbook._
import playbook.js._

class Nvd3Api(implicit sys: ActorSystem) extends Directives {

  implicit val dispatcher = sys.dispatchers.lookup("akka.http.dispatcher")

  private val playRoute = {
    path("graph") {
      get(complete(playbook.html.graphTemplate.render("graph")))
    }
  }


  /*~ path("histogram") {
        complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`, ByteString(HistogramScript("histogram").render))))
      }*/
  private val scalaJsRoute =
    path("english") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(EnglishModuleScript().render))))
    } ~ path("playoff") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(NbaHubScript().render))))
    } ~ path("speech") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(ObamaSpeechScript().render))))
    } ~ path("cluster") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(GraphScript().render))))
    } ~ path("bar-graph") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(BarGraphScript("bar-graph").render))))
    } ~ path("animals") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`,
        ByteString(AnimalsScript().render))))
    } ~ path("animals-json") {
      getFromResource("web/animals-json.json")
    }

  /*
    ~ path("cnt") {
      complete(HttpResponse(entity = Strict(ContentTypes.`text/html(UTF-8)`, ByteString(CounterScript().render))))
    }*/

  private val bookRoutes =
    path("tweets2.json") {
      getFromResource("web/tweets2.json")
    } ~ path("tweets.json") {
      getFromResource("web/tweets.json")
    } ~ path("nodelist.csv") {
      getFromResource("web/nodelist.csv")
    } ~ path("edgelist.csv") {
      getFromResource("web/edgelist.csv")
    } ~ path("ch5-12") {
      getFromResource("web/ch5_12.html")
    } ~ path("ch5-16") {
      getFromResource("web/ch5_16.html")
    } ~ path("ch5-17") {
      getFromResource("web/ch5_17.html")
    } ~ path("ch6-08") {
      getFromResource("web/ch6_08.html")
    } ~ path("flare.csv") {
      getFromResource("web/flare.csv")
    } ~ path("dendrogram") {
      getFromResource("web/dendrogram.html")
    }

  val assetsRoute =
    pathPrefix("assets" / Remaining) { file =>
      encodeResponse {
        getFromResource("public/" + file)
      }
    }

  val accidentsRoute =
    path("timeseries_data.csv") {
      getFromResource("web/accidents/timeseries_data.csv")
    } ~ path("accidents") {
      getFromResource("web/accidents/fatal-accidents.html")
    }

  val bChartRoute =
    path("bchart") {
       getFromResource("web/bump-chart/bump-chart.html")
     } ~ path("ugo.txt") {
       getFromResource("web/bump-chart/ugo.txt")
     }

  val linkedChart =
    path("linked-charts") {
      getFromResource("web/linked-charts/linked-charts.html")
    } ~ path("div9.csv") {
      getFromResource("web/linked-charts/div9.csv")
    }

  val treeR =
    path("tree") {
      getFromResource("web/tree.html")
    } ~ path("MRD.csv") {
      getFromResource("web/MRD.csv") //MRD.csv
    }

  val route = extractMaterializer { implicit mat =>
    extractExecutionContext { implicit ec =>
      extractLog { log =>
        assetsRoute ~ playRoute ~ scalaJsRoute ~ bookRoutes ~ accidentsRoute ~ bChartRoute ~ linkedChart ~ treeR
      }
    }
  }
}