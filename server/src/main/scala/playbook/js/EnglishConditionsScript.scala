package playbook.js

import scalatags.Text.all._

object EnglishConditionsScript {

  val scene = "content"

    def apply() = {
      html(
        head(
          link(rel := "stylesheet", href := "/assets/lib/bootstrap/css/bootstrap.css"),
          link(rel := "stylesheet", href := "/assets/lib/bootstrap/css/nv.d3.css")
        ),
        body(
          script(`type` := "text/javascript", src := "/assets/lib/jquery/jquery.js"),
          script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/bootstrap.js"),
          script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/raphael.js"),
          script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/word-tree-layout.js"),
          script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/wordtree.js"),

          script(`type` := "text/javascript", src := "/assets/ui-jsdeps.min.js"),
          script(`type` := "text/javascript", src := "/assets/ui-opt.js"),
          script(`type` := "text/javascript", src := "/assets/ui-launcher.js"),

          div(id := scene, cls := "center"),
          script(s"playbook.d3.EnglishConditionsModule().main('${scene}')")
        )
      )
    }
}
