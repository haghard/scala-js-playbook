package playbook

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

object SignIn {

  val SignInFormArea = ReactComponentB[((ReactEventI) => CallbackTo[Unit])]("SignFormComp")
    .stateless
    .render_P { onSignIn =>
      <.div(
        ^.cls := "container-fluid",
        <.form(
          ^.cls := "signin",
          ^.role := "form",
          <.input(
            ^.id := "login",
            ^.cls := "form-control",
            ^.`type` := "text",
            ^.placeholder := "Login"
          ),
          <.input(
            ^.id := "password",
            ^.cls := "form-control",
            ^.`type` := "password",
            ^.placeholder := "Password"
          ),
          <.button(
            "Sign in",
            ^.cls := "btn btn-lg btn-primary btn-block",
            ^.onClick ==> onSignIn
          )
        )
      )
    }.build

  val ErrorSignInFormArea = ReactComponentB[Option[String]]("SignInErrorComp")
    .stateless
    .render_P { error =>
      val ariaHidden = "aria-hidden".reactAttr
      error.fold(<.div(EmptyTag)) { msg =>
        <.div(
          msg,
          //s"Enter a valid login and password",
          ^.id := "sign-error", ^.cls := "alert alert-danger role=alert",
          <.span(^.cls := "glyphicon glyphicon-exclamation-sign", ariaHidden := "true"),
          <.span("Error:", ^.cls := "sr-only")
        )
      }
    }.build
}
