package io.gatling.demo

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://ecomm.gatling.io")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36 Edg/138.0.0.0")
  
  private val headers_0 = Map(
  		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-US,en;q=0.9",
  		"priority" -> "u=0, i",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Microsoft Edge";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows",
  		"sec-fetch-dest" -> "document",
  		"sec-fetch-mode" -> "navigate",
  		"sec-fetch-site" -> "same-origin",
  		"sec-fetch-user" -> "?1",
  		"upgrade-insecure-requests" -> "1"
  )
  
  private val headers_1 = Map(
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Microsoft Edge";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows"
  )
  
  private val headers_9 = Map(
  		"accept" -> "application/json, text/plain, */*",
  		"accept-encoding" -> "gzip, deflate, br, zstd",
  		"accept-language" -> "en-US,en;q=0.9",
  		"origin" -> "https://ecomm.gatling.io",
  		"priority" -> "u=1, i",
  		"sec-ch-ua" -> """Not)A;Brand";v="8", "Chromium";v="138", "Microsoft Edge";v="138""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> "Windows",
  		"sec-fetch-dest" -> "empty",
  		"sec-fetch-mode" -> "cors",
  		"sec-fetch-site" -> "same-site"
  )
  
  private val uri1 = "https://api-ecomm.gatling.io"

  private val scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get("/")
        .headers(headers_0)
        .resources(
          http("request_1")
            .get("/static/js/main.7b303c13.js")
            .headers(headers_1),
          http("request_2")
            .get("/static/css/main.7c407cd8.css")
            .headers(headers_1),
          http("request_3")
            .get("/static/js/315.d9ae049a.chunk.js")
            .headers(headers_1),
          http("request_4")
            .get("/static/js/391.3b61c7a8.chunk.js")
            .headers(headers_1),
          http("request_5")
            .get("/static/css/595.6517cd64.chunk.css")
            .headers(headers_1),
          http("request_6")
            .get("/static/js/595.21c2f60b.chunk.js")
            .headers(headers_1),
          http("request_7")
            .get("/gatling.png")
            .headers(headers_1),
          http("request_8")
            .get("/gatling.png")
            .headers(headers_1),
          http("request_9")
            .get(uri1 + "/products?page=0&search=")
            .headers(headers_9),
          http("request_10")
            .get(uri1 + "/session")
            .headers(headers_9)
        )
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
