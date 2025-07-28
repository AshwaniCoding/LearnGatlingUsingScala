package simulations.apiTest

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class PostApiCreatePet extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

//  val body: String =


  val scn : ScenarioBuilder = scenario(name="Create pet")
    .exec(http(requestName = "Post request to create a new pet")
    .post("/v2/pet")
    .body(StringBody("""
                       |{
                       |  "id": 18912,
                       |  "name": "dogeshGuru",
                       |  "category": {
                       |    "id": 1,
                       |    "name": "Dogesh Don"
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
                       |""".stripMargin)).asJson)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
