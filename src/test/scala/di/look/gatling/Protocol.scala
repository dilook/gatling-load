package di.look.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import ru.tinkoff.gatling.config.SimulationConfig.{getIntParam, getStringParam}

object Protocol {
  val appUrl: String = getStringParam("gatling.app.url")

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(appUrl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru-RU,ru;q=0.9")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36")
    .upgradeInsecureRequestsHeader("1")

  val acceptHeader = Map("Accept" -> "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8")
  val originHeader = Map("Origin" -> "http://www.load-test.ru:1080")


}
