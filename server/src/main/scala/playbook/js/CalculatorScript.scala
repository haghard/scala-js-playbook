package playbook.js

import scalatags.Text.TypedTag
import scalatags.Text.all._

object CalculatorScript {

  val where = "mainContainer"

  def htmlBody(injection: TypedTag[String]) =
    s"""
      |<html>
      |<head>
      |    <meta charset="UTF-8">
      |    <title>Binding.scala Deposit Calculator</title>
      |    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
      |    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css" />
      |
      |    <style type="text/css">
      |      body {
      |        display: flex;
      |        flex-direction: column;
      |        min-height: 100vh;
      |        background-color: #f9f9f9;
      |      }
      |
      |      main {
      |        flex: 1 0 auto;
      |        padding-top: 2em;
      |      }
      |    </style>
      |  </head>
      |  <body>
      |    <header>
      |      <nav role="navigation">
      |        <div class="nav-wrapper container">
      |          <a href="#" class="brand-logo">Time Deposit Calculator</a>
      |        </div>
      |      </nav>
      |    </header>
      |    <main class="container">
      |      <div id="mainContainer"></div>
      |    </main>
      |    <footer class="page-footer">
      |      <div class="container white-text">
      |        This is an example
      |      </div>
      |      <div class="footer-copyright">
      |        <div class="container">
      |          Copyright &copy; 2017 <a href="https://qwerty.io" class="deep-purple-text text-lighten-4">Company none</a>
      |        </div>
      |      </div>
      |    </footer>
      |
      |    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
      |    <script type="text/javascript" src="/assets/ui-jsdeps.min.js"></script>
      |    <script type="text/javascript" src="/assets/ui-opt.js"></script>
      |    <script type="text/javascript" src="/assets/ui-launcher.js"></script>
      |
      |    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
      |    ${injection.render}
      |</html>
    """.stripMargin

  def apply() = {
    val calcScript = script(s"playbook.other.Calculator().main('${where}')")
    htmlBody(calcScript)

    /*
    html(
      head(
        link(rel := "stylesheet", href := "/assets/lib/bootstrap/css/bootstrap.css")
      ),
      body(
        script(`type` := "text/javascript", src := "/assets/lib/jquery/jquery.js"),
        script(`type` := "text/javascript", src := "/assets/lib/bootstrap/js/bootstrap.js"),

        script(`type` := "text/javascript", src := "/assets/ui-jsdeps.min.js"),
        script(`type` := "text/javascript", src := "/assets/ui-opt.js"),
        script(`type` := "text/javascript", src := "/assets/ui-launcher.js"),


        script(s"playbook.other.Calculator().main('${where}')")
      )
    )*/
  }
}
