package simulations.feeder

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class CustomFeederTest extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("http://petstore.swagger.io")

  val customFeeder: BatchableFeederBuilder[String] = separatedValues("TestData/petData.txt", '#')

  def getSinglePet() : ChainBuilder ={

    repeat(3){

      feed(customFeeder)
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

  val scn : ScenarioBuilder = scenario("Get data from custom feeder").exec(getSinglePet())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
