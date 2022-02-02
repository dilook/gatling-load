package di.look.gatling

import di.look.gatling.Protocol._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Actions {

  val openWebTours: HttpRequestBuilder = http("openWebTours")
    .get("/webtours/")
    .check(regex("""Web Tours"""))
    .resources(
      http("header.html")
        .get("/webtours/header.html"),
      http("welcome.pl")
        .get("/cgi-bin/welcome.pl?signOff=true"),
      http("hp_logo.png")
        .get("/webtours/images/hp_logo.png")
        .headers(acceptHeader),
      http("webtours.png")
        .get("/webtours/images/webtours.png")
        .headers(acceptHeader),
      http("home.html")
        .get("/WebTours/home.html"),
      http("nav.pl")
        .get("/cgi-bin/nav.pl?in=home")
        .check(regex("name=\"userSession\" value=\"(.*)\"").saveAs("userSession")),
      http("mer_login.gif")
        .get("/WebTours/images/mer_login.gif")
        .headers(acceptHeader)
    )

  val login: HttpRequestBuilder = http("login.pl")
    .post("/cgi-bin/login.pl")
    .headers(originHeader)
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{login}")
    .formParam("password", "#{pass}")
    .formParam("login.x", "68")
    .formParam("login.y", "13")
    .formParam("JSFormSubmit", "off")
    .check(regex("""User password was correct""").exists)
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home"),
      http("login.pl")
        .get("/cgi-bin/login.pl?intro=true"),
      http("flights.gif")
        .get("/WebTours/images/flights.gif")
        .headers(acceptHeader),
      http("in_home.gif")
        .get("/WebTours/images/in_home.gif")
        .headers(acceptHeader),
      http("signoff.gif")
        .get("/WebTours/images/signoff.gif")
        .headers(acceptHeader),
      http("itinerary.gif")
        .get("/WebTours/images/itinerary.gif")
        .headers(acceptHeader)
    )

  val goToFlights: HttpRequestBuilder = http("welcome.pl")
    .get("/cgi-bin/welcome.pl?page=search")
    .check(status is 200)
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=flights"),
      http("reservations.pl")
        .get("/cgi-bin/reservations.pl?page=welcome"),
      http("home.gif")
        .get("/WebTours/images/home.gif")
        .headers(acceptHeader),
      http("in_flights.gif")
        .get("/WebTours/images/in_flights.gif")
        .headers(acceptHeader),
      http("button_next.gif")
        .get("/WebTours/images/button_next.gif")
        .headers(acceptHeader)
    )

  val findFlight: HttpRequestBuilder = http("reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .headers(originHeader)
    .formParam("advanceDiscount", "0")
    .formParam("depart", "Portland")
    .formParam("departDate", "02/02/2022")
    .formParam("arrive", "Portland")
    .formParam("returnDate", "04/02/2022")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "51")
    .formParam("findFlights.y", "11")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")

  val selectFlight: HttpRequestBuilder = http("reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .headers(originHeader)
    .formParam("outboundFlight", "552;0;02/02/2022")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "66")
    .formParam("reserveFlights.y", "8")

  val pay: HttpRequestBuilder = http("reservations.pl")
    .post("/cgi-bin/reservations.pl")
    .headers(originHeader)
    .formParam("firstName", "Di")
    .formParam("lastName", "Look")
    .formParam("address1", "")
    .formParam("address2", "")
    .formParam("pass1", " Ivan Fedorov")
    .formParam("creditCard", "00000000000000000000")
    .formParam("expDate", "2026")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("outboundFlight", "552;0;01/31/2022")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "58")
    .formParam("buyFlights.y", "13")
    .formParam(".cgifields", "saveCC")

  val goToHome: HttpRequestBuilder = http("welcome.pl")
    .get("/cgi-bin/welcome.pl?page=menus")
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home"),
      http("login.pl")
        .get("/cgi-bin/login.pl?intro=true")
    )


}
