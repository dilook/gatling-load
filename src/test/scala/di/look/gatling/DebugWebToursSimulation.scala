package di.look.gatling

import di.look.gatling.Protocol.httpProtocol
import io.gatling.core.Predef._
import io.gatling.http.Predef.Proxy
import ru.tinkoff.gatling.config.SimulationConfig._

class DebugWebToursSimulation extends Simulation {
  val proxyUrl: String = getStringParam("gatling.proxy.url")
  val proxyPort: Int = getIntParam("gatling.proxy.port")


  setUp(
    Scenario().inject(atOnceUsers(1)).disablePauses
  ).protocols(httpProtocol
//    .proxy(Proxy(proxyUrl, proxyPort))
  )


}
