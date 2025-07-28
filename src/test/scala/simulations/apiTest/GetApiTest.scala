package simulations.apiTest

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GetApiTest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io/")

  val scn : ScenarioBuilder = scenario(name = "Get the api swagger loaded")
    .exec(http(requestName = "get api fot the homepage loaded")
    .get("/v2/swagger.json"))

  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))

}
