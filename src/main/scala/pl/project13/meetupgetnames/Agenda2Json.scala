package pl.project13.meetupgetnames

import pl.project13.meetupgetnames.service.GeeCONSlurper
import org.json4s.jackson.Serialization
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

object Agenda2Json extends App {

  implicit val formats = DefaultFormats
  implicit val writeFormats = Serialization.formats(NoTypeHints)

  val slurper = new GeeCONSlurper

  val prezi = slurper.slurpPresentations()

  println(write(prezi))
}
