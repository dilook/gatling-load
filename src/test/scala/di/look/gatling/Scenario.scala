package di.look.gatling

import di.look.gatling.Actions._
import di.look.gatling.Feeders.loginPassFeeder
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}


object Scenario {
  def apply(): ScenarioBuilder = new Scenario().scn
}

class Scenario {

  val buy: ChainBuilder = group("buy ticket") {
    exec(goToFlights)
      .exec(findFlight)
      .pause(3)
      .exec(selectFlight)
      .pause(3)
      .exec(pay)
  }


  val scn: ScenarioBuilder = scenario("WebToursSimulation")
    .feed(loginPassFeeder)
    .exec(openWebTours)
    .exec(navigate)
    .pause(3)
    .exec(login)
    .pause(3)
    .exec(buy)
    .pause(3)
    .exec(goToHome)

}
