package io.gatling.demo

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class HTTPRecorder extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://127.0.0.1:53892")
    .inferHtmlResources()
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("python-requests/2.32.3")
  
  private val headers_0 = Map(
  		"Accept" -> "*/*",
  		"Content-type" -> "application/json",
  		"charset" -> "utf-8"
  )
  
  private val headers_4 = Map(
  		"Accept-Language" -> "en-US,en;q=0.9",
  		"Cache-Control" -> "no-cache",
  		"Pragma" -> "no-cache",
  		"Proxy-Connection" -> "keep-alive",
  		"Sec-Mesh-Client-Arch" -> "x86_64",
  		"Sec-Mesh-Client-Edge-Channel" -> "stable",
  		"Sec-Mesh-Client-Edge-Version" -> "138.0.3351.95",
  		"Sec-Mesh-Client-OS" -> "Windows",
  		"Sec-Mesh-Client-OS-Version" -> "10.0.19045",
  		"Sec-Mesh-Client-WebView" -> "0",
  		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36 Edg/138.0.0.0"
  )
  
  private val headers_6 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
  		"Accept-Encoding" -> "gzip, deflate, br, zstd",
  		"Accept-Language" -> "en-US,en;q=0.9",
  		"Cache-Control" -> "max-age=0",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Upgrade-Insecure-Requests" -> "1",
  		"User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36 Edg/138.0.0.0",
  		"sec-ch-ua" -> """"Not)A;Brand";v="8", "Chromium";v="138", "Microsoft Edge";v="138"""",
  		"sec-ch-ua-mobile" -> "?0",
  		"sec-ch-ua-platform" -> """"Windows""""
  )
  
  private val uri1 = "http://edge-http.microsoft.com/captiveportal/generate_204"
  
  private val uri2 = "https://ecomm.gatling.io"

  private val scn = scenario("HTTPRecorder")
    .exec(
      http("request_0")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0000_request.json"))
        .resources(
          http("request_1")
            .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
            .headers(headers_0)
            .body(RawFileBody("io/gatling/demo/httprecorder/0001_request.json")),
          http("request_2")
            .post("/api/0/buckets/aw-watcher-afk_739f9b859b4359a/heartbeat?pulsetime=185")
            .headers(headers_0)
            .body(RawFileBody("io/gatling/demo/httprecorder/0002_request.json"))
        ),
      pause(2),
      http("request_3")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0003_request.json")),
      pause(1),
      http("request_4")
        .get(uri1)
        .headers(headers_4),
      pause(1),
      http("request_5")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0005_request.json")),
      pause(1),
      http("request_6")
        .get(uri2 + "/")
        .headers(headers_6)
        .resources(
          http("request_7")
            .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
            .headers(headers_0)
            .body(RawFileBody("io/gatling/demo/httprecorder/0007_request.json"))
        ),
      pause(6),
      http("request_8")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0008_request.json")),
      pause(1),
      http("request_9")
        .post("/api/0/buckets/aw-watcher-afk_739f9b859b4359a/heartbeat?pulsetime=185")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0009_request.json")),
      pause(8),
      http("request_10")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0010_request.json")),
      pause(10),
      http("request_11")
        .post("/api/0/buckets/aw-watcher-window_739f9b859b4359a/heartbeat?pulsetime=2.0")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0011_request.json")),
      pause(6),
      http("request_12")
        .post("/api/0/buckets/aw-watcher-afk_739f9b859b4359a/heartbeat?pulsetime=185")
        .headers(headers_0)
        .body(RawFileBody("io/gatling/demo/httprecorder/0012_request.json"))
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
