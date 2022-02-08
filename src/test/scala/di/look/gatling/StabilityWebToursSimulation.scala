package di.look.gatling

import di.look.gatling.Protocol.httpProtocol
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps

class StabilityWebToursSimulation extends Simulation {

  def simpleScenarioWithPace(name: String, paceDuration: FiniteDuration): ScenarioBuilder =
    scenario(name)
      .forever(
        pace(paceDuration)
          .exec(
            Scenario()
          )
      )

  setUp(
    simpleScenarioWithPace("stability", 4 seconds)
      .inject(
        constantConcurrentUsers(50) during (1 seconds)
      ).protocols(httpProtocol)
      .throttle(
        reachRps(10).in(10 minutes),
        holdFor(1 hour)
      )
  )

}
