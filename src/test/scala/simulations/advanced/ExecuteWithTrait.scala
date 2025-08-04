package simulations.advanced

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class ExecuteWithTrait extends Simulation with AllMethods {

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val scn : ScenarioBuilder = scenario("Full end to end")
    .exec(createSinglePet("TestData/createPet.json",200))
    .exec(getSinglePet(12903,200, true, 2))
    .exec(updatePet())
    .exec(deletePet())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)

}
