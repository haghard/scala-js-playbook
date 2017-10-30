package playbook.ts

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object GanttModule {

  def toMilliseconds(minutes: Int) = minutes * 60 * 1000


  def draw(domEl: org.scalajs.dom.raw.Element): Unit = {
    //[{'value': 1380854103662},{'value': 1363641921283}];
    /*
      otherData.addRows([
        ['toTrain', 'Walk to train stop', 'walk', null, null, toMilliseconds(5), 100, null],
        ['music', 'Listen to music', 'music', null, null, toMilliseconds(70), 100, null],
        ['wait', 'Wait for train', 'wait', null, null, toMilliseconds(10), 100, 'toTrain'],
        ['train', 'Train ride', 'train', null, null, toMilliseconds(45), 75, 'wait'],
        ['toWork', 'Walk to work', 'walk', null, null, toMilliseconds(10), 0, 'train'],
        ['work', 'Sit down at desk', null, null, null, toMilliseconds(2), 0, 'toWork'],
      ]);
    */

    /*val data = js.Array(
      js.Dynamic.literal("value" -> "1380854103663"),
      js.Dynamic.literal("value" -> "1380854103664"),
      js.Dynamic.literal("value" -> "1680854103665")
    )*/

    js.Dynamic.global.google.charts.load("current", js.Dynamic.literal("packages" -> js.Array("gantt")))
    js.Dynamic.global.google.charts.setOnLoadCallback("drawGantt")
    js.Dynamic.global.drawGantt(domEl)
  }

  @JSExport
  def main(): Unit = {
    val element = org.scalajs.dom.document.getElementById("chart")
    //val el = element
    //org.scalajs.dom.console.log(org.scalajs.dom.document.getElementById("ts"))
    draw(element)
  }
}
