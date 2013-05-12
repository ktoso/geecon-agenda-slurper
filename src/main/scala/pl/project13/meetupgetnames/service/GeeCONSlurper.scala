package pl.project13.meetupgetnames.service

import org.jsoup.Jsoup
import collection.JavaConversions._
import org.jsoup.nodes.Element
import pl.project13.meetupgetnames.data.NextUpSession

class GeeCONSlurper {

  val day1 = "http://2013.geecon.org/schedule"
  val day2 = "http://2013.geecon.org/conference-day-two"
  val day3 = "http://2013.geecon.org/conference-day-three"

  def slurpPresentations(): List[NextUpSession] = {
    val all = slurpPresentations(day1, 1) :::
    slurpPresentations(day2, 2) :::
    slurpPresentations(day3, 3)

    all.filterNot(_.speaker.isEmpty)
  }


  def slurpPresentations(url: String, day: Int): List[NextUpSession] = {
    val doc = Jsoup.connect(url).get()
    val table = doc.select("#agenda_table")

    val rows = doc.select("tr")

    rows.map(parsePresentations(day, _)).flatten.toList
  }

  def parsePresentations(day: Int, el: Element): List[NextUpSession] = {
    val hours = el.select(".td_hours").text

    val startsAt = hours.split(" - ").toList.head

    val slots = el.select("td").filterNot(_.attr("class") == ".td_hours").toList

    slots.zipWithIndex map { case (slot, idx) =>
      val inRoom = idx match {
        case 1 => 1
        case 2 => 6
        case 3 => 10
        case 4 => 12
        case _ => 1
      }

      val speakers = slot.select("a").toList.map(_.text).mkString(", ")
      val title = slot.select(".lecture_title").toList.map(_.text).headOption.getOrElse("")

      val onDay = "5.%d.2013".format(14 + day)
      NextUpSession(onDay, startsAt, inRoom.toString, inRoom.toString, speakers, title)
    }
  }
}
