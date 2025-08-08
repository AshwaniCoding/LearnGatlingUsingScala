package simulations.feeder

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.protocol.HttpProtocolBuilder

class ArrayFeeder extends Simulation{

  val httpProtocol : HttpProtocolBuilder = http.baseUrl("https://petstore.swagger.io")

  def getSinglePet(): ChainBuilder = {

    val arrayFeeder = Array(
      Map("id" -> 1235813, "name" -> "feeddog1"),
      Map("id" -> 123581321, "name" -> "feeddog2"),
      Map("id" -> 1235813213, "name" -> "feeddog3")
    ).circular()

    repeat(3){
      feed(arrayFeeder)
        .exec(
          http("get single pet details for #{id} which belongs to #{name}")
            .get("/v2/pet/#{id}")
            .check(
              status.is(200),
              jsonPath("$.id").is("#{id}"),
              jsonPath("$.name").is("#{name}")
            )
        )
    }

  }

  val scn : ScenarioBuilder = scenario("Get pet").exec(getSinglePet())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
