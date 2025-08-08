package simulations.injection

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class OpenInjectionConstantUsersRateRange extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore3.swagger.io/api/v3/")

  val scn : ScenarioBuilder = scenario(name="Get Available Pets")
    .exec(http(requestName = "Get all the available pets")
      .get("pet/findByStatus?status=available"))

  setUp(scn.inject(
//    rampUsersPerSec(5).to(20).during(10)
      rampUsersPerSec(5).to(10).during(10).randomized
  ).protocols(httpProtocol))

}
