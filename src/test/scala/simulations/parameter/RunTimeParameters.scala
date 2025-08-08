package simulations.parameter

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.protocol.HttpProtocolBuilder

class RunTimeParameters extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("https://petstore3.swagger.io/api/v3/")

  def minUsers: Int = System.getProperty("minUsers","5").toInt
  def maxUsers: Int = System.getProperty("maxUsers","10").toInt
  def duration: Int = System.getProperty("duration","5").toInt

  before {
    println(s"Min Users: ${minUsers}")
    println(s"Max Users: ${maxUsers}")
    println(s"Duration: ${duration}")
  }

  val scn: ScenarioBuilder = scenario(name = "Get Available Pets")
    .exec(http(requestName = "Get all the available pets")
      .get("pet/findByStatus?status=available"))

  setUp(scn.inject(
    rampConcurrentUsers(maxUsers).to(maxUsers).during(duration)
  ).protocols(httpProtocol))

}
