package di.look.gatling

import di.look.gatling.Protocol._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Actions {

  val openWebTours: HttpRequestBuilder = http("openWebTours")
    .get("/cgi-bin/welcome.pl?signOff=true")

  val navigate: HttpRequestBuilder = http("open home page").get("/cgi-bin/nav.pl?in=home")
    .check(regex("name=\"userSession\" value=\"(.*)\"").saveAs("userSession"))

  val login: HttpRequestBuilder = http("login")
    .post("/cgi-bin/login.pl")
    .headers(originHeader)
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{login}")
    .formParam("password", "#{pass}")
    .formParam("login.x", "68")
    .formParam("login.y", "13")
    .formParam("JSFormSubmit", "off")
    .resources(
      http("load nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home"),
      http("load login.pl")
        .get("/cgi-bin/login.pl?intro=true")
        .check(regex("""Welcome, <b>#{login}</b>, to the Web Tours reservation pages""").exists)
    )

  val goToFlights: HttpRequestBuilder = http("go to flights")
    .get("/cgi-bin/welcome.pl?page=search")
    .check(status is 200)
    .resources(
      http("load nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=flights"),
      http("load reservations.pl")
        .get("/cgi-bin/reservations.pl?page=welcome")
        .check(
          css("select[name=\"depart\"] option").findAll.saveAs("cities"),
          css("input[name=\"departDate\"]", "value").find.saveAs("departDate"),
          css("input[name=\"returnDate\"]", "value").find.saveAs("returnDate")
        )
    )

  val findFlight: HttpRequestBuilder = http("find flights")
    .post("/cgi-bin/reservations.pl")
    .headers(originHeader)
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{cities.random()}")
    .formParam("departDate", "#{departDate}")
    .formParam("arrive", "#{cities.random()}")
    .formParam("returnDate", "#{returnDate}")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "51")
    .formParam("findFlights.y", "11")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(
      css("input[name=\"outboundFlight\"]", "value").findRandom.saveAs("outboundFlight"),
    )

  val selectFlight: HttpRequestBuilder = http("reserve flight")
    .post("/cgi-bin/reservations.pl")
    .headers(originHeader)
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    .formParam("seatType", "Coach")
    .formParam("seatPref", "None")
    .formParam("reserveFlights.x", "66")
    .formParam("reserveFlights.y", "8")

  val pay: HttpRequestBuilder = http("buy ticket")
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
    .formParam("outboundFlight", "#{outboundFlight}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "58")
    .formParam("buyFlights.y", "13")
    .formParam(".cgifields", "saveCC")

  val goToHome: HttpRequestBuilder = http("go to home")
    .get("/cgi-bin/welcome.pl?page=menus")
    .resources(
      http("load nav.pl")
        .get("/cgi-bin/nav.pl?page=menu&in=home"),
      http("load login.pl")
        .get("/cgi-bin/login.pl?intro=true")
    )


}
