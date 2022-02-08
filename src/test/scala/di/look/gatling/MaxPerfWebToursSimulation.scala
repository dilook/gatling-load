package di.look.gatling

import di.look.gatling.Protocol.httpProtocol
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.language.postfixOps

class MaxPerfWebToursSimulation extends Simulation {

  def simpleScenarioWithPace(name: String, paceDuration: FiniteDuration): ScenarioBuilder =
    scenario(name)
      .forever(
        pace(paceDuration)
          .exec(
            Scenario()
          )
      )

  setUp(
    simpleScenarioWithPace("maxperf", 4 seconds)
      .inject(
        constantConcurrentUsers(50) during (1 seconds)
      ).protocols(httpProtocol)
      .throttle(
        reachRps(6).in(5 minutes),
        holdFor(2 minutes),
        jumpToRps(8),
        holdFor(2 minutes),
        jumpToRps(10),
        holdFor(2 minutes),
        jumpToRps(12),
        holdFor(2 minutes),
        jumpToRps(14),
        holdFor(2 minutes),
        jumpToRps(16),
        holdFor(2 minutes),
        jumpToRps(18),
        holdFor(2 minutes),
        jumpToRps(20),
        holdFor(2 minutes)
      )
  )

}
