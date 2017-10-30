package playbook.ts

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object TsModule {

  def draw(where: String, domEl: org.scalajs.dom.raw.Element): Unit = {
    //[{'value': 1380854103662},{'value': 1363641921283}];
    val data = js.Array(
      js.Dynamic.literal("value" -> "1380854103662"),
      js.Dynamic.literal("value" -> "1380854103663"),
      js.Dynamic.literal("value" -> "1380854103664"),
      js.Dynamic.literal("value" -> "1680854103665")
    )

    js.Dynamic.global.timeseries(where, data, false)
  }

  @JSExport
  def main(): Unit = {
    val element = org.scalajs.dom.document.getElementById("ts")
    //val el = element
    //org.scalajs.dom.console.log(org.scalajs.dom.document.getElementById("ts"))
    draw("timeseries", element)
  }
}
