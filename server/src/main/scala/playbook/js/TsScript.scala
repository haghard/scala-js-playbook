package playbook.js

import scalatags.Text.TypedTag
import scalatags.Text.all._

object TsScript {

  def htmlBody(form: TypedTag[String]) = {
      form.render
  }

  def apply() = {
    val body0 =
    html(
      head(
        link(rel := "stylesheet", href := "/assets/lib/bootstrap/css/tsstyle.css"),
        link(href := "http://fonts.googleapis.com/css?family=Open+Sans:400,700", rel := "stylesheet", `type` := "text/css")
      ),
      body(

        script(src := "http://d3js.org/d3.v3.min.js", charset := "utf-8"),
        script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/d3.v3.min.js"),
        script(`type` := "text/javascript", src := "http://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.1.0/lodash.min.js"),
        script(`type` := "text/javascript", src := "http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"),
        script(`type` := "text/javascript", src := "http://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js"),
        script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/timeseries.js"),
        
        script(`type` := "text/javascript", src := "/assets/ui-jsdeps.min.js"),
        script(`type` := "text/javascript", src := "/assets/ui-opt.js"),
        
        div(id := "ts", cls := "timeseries"),

        script(
          """
            |window.onload = function() {
            |        function getData(start, end, amount) {
            |            var data = [];
            |            for (i = 0; i < amount; i++) {
            |                data.push({
            |                    'value': (randomDate(start, end))
            |                })
            |            }
            |            return data;
            |        }
            |        function randomDate(start, end) {
            |            return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime())).valueOf();
            |        }
            |        var amount = 100;
            |        if (window.innerWidth < 800)
            |          amount = 30;
            |          
            |        timeseries('timeseries one', getData(new Date(2012, 1, 1), new Date(2015, 1, 2), amount), true);
            |        timeseries('timeseries two', getData(new Date(2015, 1, 1), new Date(2015, 1, 2), amount), false);
            |    }
          """.stripMargin)

        //script("playbook.ts.TsModule().main()")
      )
    )

    htmlBody(body0)

  }
}
