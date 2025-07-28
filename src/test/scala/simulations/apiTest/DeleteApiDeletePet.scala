package simulations.apiTest

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class DeleteApiDeletePet extends  Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario(name="Delete Pet")
    .exec(http(requestName = "Api to delete a pet")
    .delete("/v2/pet/18912"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
