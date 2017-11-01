package playbook.ts

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object GanttModule {

  def toMilliseconds(minutes: Int) = minutes * 60 * 1000

  def draw(): Unit = {
    org.scalajs.dom.console.log("before")

    val columns = js.Array(
      js.Dynamic.literal("f" -> "string", "s" -> "Task ID"),
      js.Dynamic.literal("f" -> "string", "s" -> "Task Name"),
      js.Dynamic.literal("f" -> "string", "s" -> "Resource"),
      js.Dynamic.literal("f" -> "date", "s" -> "Start"),
      js.Dynamic.literal("f" -> "date", "s" -> "End"),
      js.Dynamic.literal("f" -> "number", "s" -> "Duration"),
      js.Dynamic.literal("f" -> "number", "s" -> "Percent Complete"),
      js.Dynamic.literal("f" -> "string", "s" -> "Task ID")
    )

    val rows =
      js.Array(
        js.Array[js.Any]("toTrain", "Walk to train stop", "walk", null, null, toMilliseconds(5), 100, null),
        js.Array[js.Any]("music", "Listen to music", "music", null, null, toMilliseconds(70), 100, null),
        js.Array[js.Any]("wait", "Wait for train", "wait", null, null, toMilliseconds(10), 100, "toTrain"),
        js.Array[js.Any]("train", "Train ride", "train", null, null, toMilliseconds(45), 75, "wait"),
        js.Array[js.Any]("toWork", "Walk to work", "walk", null, null, toMilliseconds(10), 0, "train"),
        js.Array[js.Any]("work", "Sit down at desk", null, null, null, toMilliseconds(2), 0, "toWork")
      )

    js.Dynamic.global.setColumns(columns)
    js.Dynamic.global.setRows(rows)
    js.Dynamic.global.init()

    org.scalajs.dom.console.log("after")
  }

  @JSExport
  def main(): Unit = {
    draw()
  }
}
/*
val element = org.scalajs.dom.document.getElementById("chart")
val document = js.Dynamic.global.document
val element = document.getElementById("gnt")
org.scalajs.dom.console.log(element)
*/