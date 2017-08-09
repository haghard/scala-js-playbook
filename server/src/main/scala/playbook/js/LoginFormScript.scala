package playbook.js

import scalatags.Text.TypedTag
import scalatags.Text.all._

//http://materializecss.com/forms.html
object LoginFormScript {
  val where = "mainContent"

  def htmlBody(form: TypedTag[String]) =
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
       |
       |      .input-field label {
       |        color: #dd1144;
       |      }
       |
       |      .input-field input[type=text]:focus + label {
       |        color: #000;
       |      }
       |
       |    </style>
       |  </head>
       |  <body>
       |    <header>
       |      <nav role="navigation">
       |        <div class="nav-wrapper container">
       |          <a href="#" class="brand-logo">Login Form</a>
       |        </div>
       |      </nav>
       |    </header>
       |    <main class="container">
       |      <div id="mainContent">
       |      ${form.render}
       |      </div>
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
       |</html>
      """.stripMargin

  def apply() = {
    val form0 =
      div(cls := "row")(
        form(cls := "col s12")(
          div(cls := "row")(
            div(cls := "input-field col s6")(
              i(attr("class") := "material-icons prefix")("account_circle"),
              input(id := "icon_prefix", `type` := "text", cls := "validate"),
              label(`for` := "icon_prefix")("First Name")
            )
          )
        )
      )

    val form1 =
      div(cls := "row")(
        form(cls := "col s12")(
          div(cls := "row")(
            div(cls := "input-field col s6")(
              input(id := "login", `type` := "text", placeholder := "Placeholder"),
              label(`for` := "login")("Login")
            )
          ),
          div(cls := "row")(
            div(cls := "input-field col s6")(
              input(id := "password", `type` := "password"),
              label(`for` := "password")("password")
            )
          ),
          div(cls := "input-field col s12")(
            i(cls := "material-icons prefix")("textsms"),
            input(`type` := "text", id := "autocomplete-input", cls := "autocomplete"),
            label(`for` := "autocomplete-input")("Autocomplete")
          )
        )
      )

    htmlBody(form1)
  }
}
