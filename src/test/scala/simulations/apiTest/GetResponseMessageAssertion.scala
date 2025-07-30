package simulations.apiTest

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class GetResponseMessageAssertion extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val postPet : ScenarioBuilder = scenario("Post Pet")
    .exec(http(requestName = "Create a new pet")
      .post("/v2/pet")
      .body(RawFileBody("TestData/createPet.json")).asJson
      .check(
        status.in(expected = 200 to 204),
        jsonPath("$.id").is("12903"),
        jsonPath("$.name").is("Mr. Dogesh Guru")
      )
    )

  val getPet : ScenarioBuilder = scenario("Get Pet")
    .exec(http(requestName = "Get already created pet")
      .get("/v2/pet/12903")
      .check(
        status.in(expected = 200 to 204),
        jsonPath("$.id").is("12903"),
        jsonPath("$.name").is("Mr. Dogesh Guru")
      ))

  val putPet : ScenarioBuilder = scenario(name="Update Pet")
    .exec(http(requestName = "Update already created pet")
      .put("/v2/pet")
      .body(RawFileBody("TestData/updatePet.json")).asJson
      .check(
        status.in(expected = 200 to 204),
        jsonPath("$.id").is("12903"),
        jsonPath("$.name").is("Mr. Dogesh Don")
      ))

  val againGetPet : ScenarioBuilder = scenario("Again Get Pet")
    .exec(http(requestName = "Get updated pet")
      .get("/v2/pet/12903")
      .check(
        status.in(expected = 200 to 204),
        jsonPath("$.id").is("12903"),
        jsonPath("$.name").is("Mr. Dogesh Don")
      ))

  val deletePet : ScenarioBuilder = scenario(name="Delete Pet")
    .exec(http(requestName = "Api to delete a pet")
      .delete("/v2/pet/12903")
      .check(
        status.in(expected = 200 to 204)
      ))

  setUp(
    postPet.inject(atOnceUsers(1)),
    getPet.inject(atOnceUsers(1)),
    putPet.inject(atOnceUsers(1)),
    againGetPet.inject(atOnceUsers(1)),
    deletePet.inject(atOnceUsers(1))
  ).protocols(httpProtocol)

}
