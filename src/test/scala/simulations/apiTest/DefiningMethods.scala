package simulations.apiTest

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.protocol.HttpProtocolBuilder

class DefiningMethods extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  def getSinglePet() : ChainBuilder = {
    exec(
      http("Get a single pet")
        .get("/v2/pet/12903")
    )
  }

  def createSinglePet() : ChainBuilder = {
    exec(
      http("Create a new pet")
        .post("/v2/pet")
        .body(RawFileBody("TestData/createPet.json")).asJson
    )
  }

  def deletePet() : ChainBuilder = {
    exec(http("Delete a single pet")
      .delete("/v2/pet/12903")
    )
  }

  def updatePet() : ChainBuilder = {
    exec(http("Update a pet")
      .put("/v2/pet")
      .body(RawFileBody("TestData/updatePet.json")).asJson
    )
  }

  val scn : ScenarioBuilder = scenario("Full end to end")
    .exec(createSinglePet())
    .exec(getSinglePet())
    .exec(updatePet())
    .exec(deletePet())

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)

}
