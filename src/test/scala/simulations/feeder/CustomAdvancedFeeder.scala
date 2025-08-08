package simulations.feeder

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.protocol.HttpProtocolBuilder

class CustomAdvancedFeeder extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")
  val numIterator : Iterator[Int] = (1 to 10).iterator
  val customFeeder : Iterator[Map[String, Int]] = Iterator.continually(Map("id" -> numIterator.next()))

  def getSinglePet(): ChainBuilder = {

    repeat(10){
      feed(customFeeder)
        .exec(
          http("get single pet details for #{id}")
            .get("/v2/pet/#{id}")
            .check(
              status.is(200),
              jsonPath("$.id").is("#{id}"),
            )
        )
    }

  }

  val scn : ScenarioBuilder = scenario("Get pet").exec(getSinglePet())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
