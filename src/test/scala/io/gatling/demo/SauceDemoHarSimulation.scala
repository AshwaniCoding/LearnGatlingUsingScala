package io.gatling.demo

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SauceDemoHarSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://www.saucedemo.com")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36")
  
  private val headers_0 = Map(
  		"Upgrade-Insecure-Requests" -> "1",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val headers_1 = Map(
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val headers_4 = Map(
  		"Origin" -> "https://www.saucedemo.com",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val headers_6 = Map(
  		"accept" -> "*/*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-GB,en-US;q=0.9,en;q=0.8",
  		"priority" -> "u=2",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows",
  		"sec-fetch-dest" -> "manifest",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-origin"
  )
  
  private val uri2 = "https://fonts.gstatic.com/s"

  private val scn = scenario("SauceDemoHarSimulation")
    .exec(
      http("request_0")
        .get("/?/checkout-step-one.html")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/static/media/menu3x.52cc17a3.svg")
            .headers(headers_1),
          http("request_2")
            .get("/static/media/close@3x.a30a8a1d.svg")
            .headers(headers_1),
          http("request_3")
            .get("/static/media/cart3x.3669d74a.svg")
            .headers(headers_1),
          http("request_4")
            .get(uri2 + "/dmsans/v16/rP2Yp2ywxg089UriI5-g4vlH9VoD8Cmcqbu0-K4.woff2")
            .headers(headers_4),
          http("request_5")
            .get(uri2 + "/dmmono/v15/aFTU7PB1QTsUX8KYthqQBA.woff2")
            .headers(headers_4),
          http("request_6")
            .get("/manifest.json")
            .headers(headers_6)
        )
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
