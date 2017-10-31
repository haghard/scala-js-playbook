package playbook.js

import scalatags.Text.all._

//https://developers.google.com/chart/interactive/docs/gallery/ganttchart
object GanttScript {

  def apply() = {     
    html(
          head(
            link(rel := "stylesheet", href := "/assets/lib/bootstrap/css/bootstrap.css")
          ),
          body(
            /*script(`type` := "text/javascript", src := "https://www.gstatic.com/charts/loader.js"),*/
            script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/loader.js"),
            script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/gantt.js"),

            script(`type` := "text/javascript", src := "/assets/ui-jsdeps.min.js"),
            script(`type` := "text/javascript", src := "/assets/ui-opt.js"),
            script(`type` := "text/javascript", src := "/assets/ui-launcher.js"),

            div(id := "gnt", cls := "center"),
            script("playbook.ts.GanttModule().main()")
          )
    )
  }
}
