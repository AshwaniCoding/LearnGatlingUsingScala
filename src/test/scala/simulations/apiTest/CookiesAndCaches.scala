package simulations.apiTest

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class CookiesAndCaches extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val getAllPendingPets : ScenarioBuilder = scenario("Get Pending Pets")

    //Cookies and Caches
    .exec(flushHttpCache)
    .exec(flushCookieJar)
    .exec(flushSessionCookies)
    .exec(http(requestName = "Get all the pending pets available in swagger")
      .get("/v2/pet/findByStatus?status=pending")
      .check(status is 200)
      .check(jsonPath("$[1].id").saveAs("petId"))
      .check(jsonPath("$[1].name").saveAs("petName"))
    )

    .pause(3)
    .exec(flushHttpCache)
    .exec(flushCookieJar)
    .exec(flushSessionCookies)
    .exec(http(requestName = "Get the pending pet which is saved")
      .get("/v2/pet/#{petId}")
      .check(
        status.in(expected = 200 to 204 ),
        jsonPath("$.id").is("#{petId}"),
        jsonPath("$.name").is("#{petName}")
      )
    )

  setUp(
    getAllPendingPets.inject(atOnceUsers(1))
  ).protocols(httpProtocol)

}
