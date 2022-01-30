package di.look.gatling

import di.look.gatling.Actions._
import di.look.gatling.Protocol.httpProtocol
import io.gatling.core.Predef._

class WebToursSimulation extends Simulation {


  private val scn = scenario("WebToursSimulation")
    .feed(Feeders.loginPassFeeder)
    .exec(openWebTours)
    .pause(3)
    .exec(login)
    .pause(3)
    .exec(goToFlights)
    .pause(3)
    .exec(findFlight)
    .pause(3)
    .exec(selectFlight)
    .pause(3)
    .exec(pay)
    .pause(3)
    .exec(goToHome)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
