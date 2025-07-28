package simulations.apiTest

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class PutApiUpdatePet extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  val body: String =
    """
      |{
      |  "id": 18912,
      |  "name": "dogeshGuruGuruGuru",
      |  "category": {
      |    "id": 1,
      |    "name": "Dogesh Mr. Don"
      |  },
      |  "photoUrls": [
      |    "string"
      |  ],
      |  "tags": [
      |    {
      |      "id": 0,
      |      "name": "string"
      |    }
      |  ],
      |  "status": "available"
      |}
      |""".stripMargin

  val scn : ScenarioBuilder = scenario(name="Update Pet")
    .exec(http(requestName = "Update pet which is already created")
    .put("/v2/pet")
    .body(StringBody(body)).asJson)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
