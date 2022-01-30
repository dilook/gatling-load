package di.look.gatling

import di.look.gatling.Protocol._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Actions {

  val openWebTours: HttpRequestBuilder = http("openWebTours")
    .get("/webtours/")
    .headers(upgradeInsecureRequestHeader)
    .check(regex("""Welcome to the Web Tours site."""))
    .resources(
      http("header.html")
        .get("/webtours/header.html")
        .headers(upgradeInsecureRequestHeader),
      http("favicon.ico")
        .get("/favicon.ico")
        .headers(acceptHeader)
        .check(status.is(404)),
      http("welcome.pl")
        .get("/cgi-bin/welcome.pl?signOff=true")
        .headers(upgradeInsecureRequestHeader)
        .check(regex("name=\"userSession\" value=\"(.*)\"").saveAs("userSession")),
      http("hp_logo.png")
        .get("/webtours/images/hp_logo.png")
        .headers(acceptHeader),
      http("webtours.png")
        .get("/webtours/images/webtours.png")
        .headers(acceptHeader),
      http("home.html")
        .get("/WebTours/home.html")
        .headers(upgradeInsecureRequestHeader),
      http("nav.pl")
        .get("/cgi-bin/nav.pl?in=home")
        .headers(upgradeInsecureRequestHeader),
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
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home")
        .headers(upgradeInsecureRequestHeader),
      http("login.pl")
        .get("/cgi-bin/login.pl?intro=true")
        .headers(upgradeInsecureRequestHeader),
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
    .headers(upgradeInsecureRequestHeader)
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=flights")
        .headers(upgradeInsecureRequestHeader),
      http("reservations.pl")
        .get("/cgi-bin/reservations.pl?page=welcome")
        .headers(upgradeInsecureRequestHeader),
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
    .formParam("departDate", "01/31/2022")
    .formParam("arrive", "Portland")
    .formParam("returnDate", "02/01/2022")
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
    .formParam("outboundFlight", "552;0;01/31/2022")
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
    .headers(upgradeInsecureRequestHeader)
    .resources(
      http("nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home")
        .headers(upgradeInsecureRequestHeader),
      http("login.pl")
        .get("/cgi-bin/login.pl?intro=true")
        .headers(upgradeInsecureRequestHeader)
    )


}