package simulations.injection

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class ThrottleTest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore3.swagger.io/api/v3/")

  val scn : ScenarioBuilder = scenario(name="Get Available Pets")
    .exec(http(requestName = "Get all the available pets")
      .get("pet/findByStatus?status=available"))

  setUp(scn.inject(
    constantConcurrentUsers(40).during(20)
  )).throttle(
    reachRps(50).in(10),
      holdFor(10),
      jumpToRps(25),
      holdFor(10)
    )
    .protocols(httpProtocol)

}
