package playbook.js

import scalatags.Text.all._

//https://developers.google.com/chart/interactive/docs/gallery/ganttchart
object GanttScript {

  def apply() = {
    //<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    val where = "chart"
    html(
          head(
            script(`type` := "text/javascript", src := "https://www.gstatic.com/charts/loader.js"),
            script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/gantt.js"),
            script(`type` := "text/javascript", src := "/assets/ui-jsdeps.min.js"),
            script(`type` := "text/javascript", src := "/assets/ui-opt.js"),
            //script("google.charts.load('current', {'packages':['gantt']}) "),
            //script("google.charts.setOnLoadCallback(drawGantt) ")
          ),
          body(
            div(id := where, cls := "center"),
            /*
            script(
              """
                |google.charts.load('current', {'packages':['gantt']});
                |google.charts.setOnLoadCallback(drawGantt);
                |"playbook.ts.GanttModule().main()"
              """.stripMargin)
              */

            script("playbook.ts.GanttModule().main()")

            /*script(
              s"""
                |   google.charts.load('current', {'packages':['gantt']});
                |   google.charts.setOnLoadCallback(drawChart);
                |
                |   function daysToMilliseconds(days) {
                |     return days * 24 * 60 * 60 * 1000;
                |   }
                |
                |    function drawChart() {
                |
                |      var data = new google.visualization.DataTable();
                |      data.addColumn('string', 'Task ID');
                |      data.addColumn('string', 'Task Name');
                |      data.addColumn('date', 'Start Date');
                |      data.addColumn('date', 'End Date');
                |      data.addColumn('number', 'Duration');
                |      data.addColumn('number', 'Percent Complete');
                |      data.addColumn('string', 'Dependencies');
                |
                |      data.addRows([
                |        ['Research', 'Find sources',
                |         new Date(2015, 0, 1), new Date(2015, 0, 5), null,  100,  null],
                |        ['Write', 'Write paper',
                |         null, new Date(2015, 0, 9), daysToMilliseconds(3), 25, 'Research,Outline'],
                |        ['Cite', 'Create bibliography',
                |         null, new Date(2015, 0, 7), daysToMilliseconds(1), 20, 'Research'],
                |        ['Complete', 'Hand in paper',
                |         null, new Date(2015, 0, 10), daysToMilliseconds(1), 0, 'Cite,Write'],
                |        ['Outline', 'Outline paper',
                |         null, new Date(2015, 0, 6), daysToMilliseconds(1), 100, 'Research']
                |      ]);
                |
                |      var options = {
                |        height: 275
                |      };
                |      var options2 = {
                |          height: 275,
                |          gantt: {
                |            criticalPathEnabled: true,
                |            criticalPathStyle: {
                |              stroke: '#e64a19',
                |              strokeWidth: 5
                |            }
                |          }
                |        };
                |
                |      var chart = new google.visualization.Gantt(document.getElementById('${where}'));
                |
                |      chart.draw(data, options2);
                |    }
              """.stripMargin)
              */
          )
    )
  }
}
