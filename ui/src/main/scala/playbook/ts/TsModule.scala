package playbook.ts

import aleastchs.googleCharts.GoogleVisualization
import aleastchs.googleCharts.GoogleVisualization.{DataTable, Timeline}
import aleastchs.googleCharts.helpers.chartsHelp.{TimelineHelper, TimelineOption}

import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js

@JSExport
object TsModule {

  def toMilliseconds(n: Int) = (n  * 60 * 1000).toString

  def draw(where: String, domEl: org.scalajs.dom.raw.Element): Unit = {

    //[{'value': 1380854103662},{'value': 1363641921283}];
    /*val data = js.Array(
      js.Dynamic.literal("value" -> "1380854103662"),
      js.Dynamic.literal("value" -> "1380854103663"),
      js.Dynamic.literal("value" -> "1380854103664"),
      js.Dynamic.literal("value" -> "1680854103665")
    )*/


    //import aleastchs.googleCharts.google.visualization.Timeline

    /*
    var otherData = new google.visualization.DataTable();
      otherData.addColumn('string', 'Task ID');
      otherData.addColumn('string', 'Task Name');
      otherData.addColumn('string', 'Resource');
      otherData.addColumn('date', 'Start');
      otherData.addColumn('date', 'End');
      otherData.addColumn('number', 'Duration');
      otherData.addColumn('number', 'Percent Complete');
      otherData.addColumn('string', 'Dependencies');

      otherData.addRows([
        ['toTrain', 'Walk to train stop', 'walk', null, null, toMilliseconds(5), 100, null],
        ['music', 'Listen to music', 'music', null, null, toMilliseconds(70), 100, null],
        ['wait', 'Wait for train', 'wait', null, null, toMilliseconds(10), 100, 'toTrain'],
        ['train', 'Train ride', 'train', null, null, toMilliseconds(45), 75, 'wait'],
        ['toWork', 'Walk to work', 'walk', null, null, toMilliseconds(10), 0, 'train'],
        ['work', 'Sit down at desk', null, null, null, toMilliseconds(2), 0, 'toWork'],
      ]);
     */


    //val optional_version : _root_.scala.Predef.String
    org.scalajs.dom.console.log("1")
    val dt = new DataTable()
    org.scalajs.dom.console.log("dt")

    /*dt.addColumn(Array("string", "Task ID"))
    dt.addColumn(Array("string", "Task Name"))
    dt.addColumn(Array("string", "Resource"))
    dt.addColumn(Array("date", "Start"))
    dt.addColumn(Array("date", "End"))
    dt.addColumn(Array("number", "Duration"))
    dt.addColumn(Array("number", "Percent Complete"))
    dt.addColumn(Array("string", "Dependencies"))
    org.scalajs.dom.console.log("columns")


    dt.addRow(js.Array[js.Any]("toTrain", "Walk to train stop", "walk", null, null, toMilliseconds(5), 100, null))
    dt.addRow(js.Array[js.Any]("music", "Listen to music", "music", null, null, toMilliseconds(70), 100, null))
    dt.addRow(js.Array[js.Any]("wait", "Wait for train", "wait", null, null, toMilliseconds(10), 100, "toTrain"))

    org.scalajs.dom.console.log("draw")

    val timeline = new Timeline(domEl.asInstanceOf[js.Dynamic])
    TimelineHelper(dt, timeline, TimelineOption(200,300, "x"))
    */
    
    //timeline.draw(dt)

    //js.Dynamic.global.timeseries(where, data, false)
  }

  @JSExport
  def main(): Unit = {
    val element = org.scalajs.dom.document.getElementById("Timeline")
    //val el = element
    org.scalajs.dom.console.log("element")
    draw("timeseries", element)
  }
}
