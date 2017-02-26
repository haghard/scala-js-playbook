import sbt._

val scalaV = "2.11.8"
val akkaVersion = "2.4.17"
val version = "0.1"

name := "scala-js-playbook"

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

updateOptions in Global := updateOptions.in(Global).value.withCachedResolution(true)

lazy val server = (project in file("server")).settings(
  resolvers ++= Seq(
    "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
  ),

  scalacOptions in(Compile, console) := Seq("-feature", "-Xfatal-warnings", "-deprecation", "-unchecked"),
  scalaVersion := scalaV,
  scalaJSProjects := Seq(ui),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile <<= (compile in Compile).dependsOn(scalaJSPipeline, cpCss),

  name := "server",

  fork in runMain := true,
  javaOptions in run ++= Seq("-Xms128m", "-Xmx1024m"),

  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,

    "com.typesafe.akka" %% "akka-http"  % "10.0.4",

    "ch.qos.logback"  %   "logback-classic" % "1.1.2",

    "com.vmunier"     %%  "scalajs-scripts" % "1.1.0", //Twirl templates to link Scala.js output scripts into a HTML page.

    "com.lihaoyi"     %%  "scalatags"       % "0.6.2",
    "org.webjars"     %   "bootstrap"       % "3.3.6",
    "org.scalatest"    %% "scalatest"       % "3.0.1" % "test"
  ),

  WebKeys.packagePrefix in Assets := "public/",

  (managedClasspath in Runtime) += (packageBin in Assets).value,

  mainClass in assembly := Some("playbook.Application"),

  assemblyJarName in assembly := s"scala-js-playbook-${version}.jar",

  // Resolve duplicates for Sbt Assembly
  assemblyMergeStrategy in assembly := {
    case PathList(xs@_*) if xs.last == "io.netty.versions.properties" => MergeStrategy.rename
    case other => (assemblyMergeStrategy in assembly).value(other)
  }


).enablePlugins(SbtWeb, SbtTwirl, JavaAppPackaging).dependsOn(sharedJvm)

//for debugging
def cpCss() = (baseDirectory) map { dir =>
  def execute() = {
    IO.copyFile(dir / "src" / "main" / "twirl" / "playbook"/ "main.css",
      dir / "target" /"web"/"web-modules"/"main"/"webjars"/"lib"/"bootstrap"/"css"/"main.css")

    IO.copyFile(dir /"src"/"main"/"resources"/"chat.css",
      dir/"target"/"web"/"web-modules"/"main"/"webjars"/"lib"/"bootstrap"/"css"/"chat.css")

    Process(s"cp ${dir}/src/main/resources/graph/graph.css ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/css/").!
    Process(s"cp ${dir}/src/main/resources/graph/graph.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!

    Process(s"cp ${dir}/src/main/resources/nv.d3.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    //Process(s"cp ${dir}/src/main/resources/d3.min.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/d3.v3.min.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/colorbrewer.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/d3.v4.min.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!

    Process(s"cp ${dir}/src/main/resources/queue.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!

    Process(s"cp ${dir}/src/main/resources/wordtree/raphael.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/wordtree/word-tree-layout.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/wordtree/wordtree.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!

    Process(s"cp ${dir}/src/main/twirl/playbook/nv.d3.css ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/css/").!
    Process(s"cp ${dir}/src/main/resources/tree.css ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/css/").!


    Process(s"cp ${dir}/src/main/resources/web/linked-charts/area1.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!
    Process(s"cp ${dir}/src/main/resources/web/linked-charts/area2.js ${dir}/target/web/web-modules/main/webjars/lib/bootstrap/js").!

  }

  println("Coping resources ...")
  haltOnCmdResultError(execute())
}

def haltOnCmdResultError(result: Int) {
  if (result != 0) throw new Exception("Build failed")
}

lazy val ui = (project in file("ui")).settings(

  resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,

  libraryDependencies ++= Seq(
    "org.singlespaced" %%% "scalajs-d3" % "0.3.3", //the version for scala 2.12 is 0.3.4
    "com.github.japgolly.scalajs-react" %%% "core"    % "0.11.3",
    "com.github.japgolly.scalajs-react" %%% "extra"   % "0.11.3",
    "com.github.yoeluk"                 %%% "raphael-scala-js" % "0.2-SNAPSHOT"),

  jsDependencies ++= Seq(
    "org.webjars" % "jquery" % "2.1.3" / "2.1.3/jquery.js",
    "org.webjars.bower" % "react" % "15.3.2"
        /        "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "15.3.2"
        /         "react-dom.js"
        minified  "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM",

      "org.webjars.bower" % "react" % "15.3.2"
        /         "react-dom-server.js"
        minified  "react-dom-server.min.js"
        dependsOn "react-dom.js"
        commonJSName "ReactDOMServer"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
    scalaVersion := scalaV,
    libraryDependencies ++= Seq("com.lihaoyi" %%% "upickle" % "0.4.3")
  ).jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
