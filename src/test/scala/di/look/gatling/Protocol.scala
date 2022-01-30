package di.look.gatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object Protocol {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://www.load-test.ru:1080")
    .inferHtmlResources(AllowList(), DenyList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0")


  val upgradeInsecureRequestHeader = Map("Upgrade-Insecure-Requests" -> "1")
  val acceptHeader = Map("Accept" -> "image/avif,image/webp,*/*")
  val originHeader = Map(
    "Origin" -> "http://www.load-test.ru:1080",
    "Upgrade-Insecure-Requests" -> "1"
  )


}
