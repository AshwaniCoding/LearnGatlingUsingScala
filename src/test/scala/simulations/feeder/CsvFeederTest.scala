package simulations.feeder

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.protocol.HttpProtocolBuilder

class CsvFeederTest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("http://petstore.swagger.io")

  val csvFeeder = csv("TestData/petData.csv").circular()

  def getSinglePet() : ChainBuilder ={

    repeat(3){

      feed(csvFeeder)
        .exec(
          http(requestName = "get a single pet")
            .get("/v2/pet/#{id}")
            .check(status.is(200),
              jsonPath("$.id").is("#{id}"),
              jsonPath("$.name").is("#{name}")

            )
        )
    }
  }

  val scn : ScenarioBuilder = scenario("Get data from csv").exec(getSinglePet())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
