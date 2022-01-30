package di.look.gatling

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder

object Feeders {

  val loginPassFeeder: BatchableFeederBuilder[String] = csv("myWords.csv").random.eager
}
