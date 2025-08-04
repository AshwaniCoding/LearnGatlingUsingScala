package simulations.advanced

import io.gatling.core.Predef.{atOnceUsers, scenario}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class AuthenticationAuthorizationTest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io/").header("Authorization","special_key")

  val scn : ScenarioBuilder = scenario(name = "Get the api swagger loaded")
    .exec(http(requestName = "get api fot the homepage loaded")
      .get("/v2/swagger.json"))

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))


}
