package simulations.apiTest

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class GetApiGetPet extends  Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Get Pet")
    .exec(http(requestName = "Get the pet by id")
    .get("/v2/pet/18912"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
