package simulations.advanced

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder

trait AllMethods {

  def getSinglePet(petId: Int, statusCode: Int, repeatStatus: Boolean, repeatCount : Int): ChainBuilder = {

    val getRequest = exec(
      http("Get a single pet")
        .get(s"/v2/pet/${petId}")
        .check(status.is(statusCode))
    )
    if (repeatStatus) {
      repeat(repeatCount) {
        getRequest
      }
    } else {
      getRequest
    }
  }

  def createSinglePet(requestFileName: String, statusCode: Int): ChainBuilder = {
    exec(
      http("Create a new pet")
        .post("/v2/pet")
        .body(RawFileBody(requestFileName)).asJson
        .check(status.is(statusCode))
    )
  }

  def deletePet(): ChainBuilder = {
    exec(http("Delete a single pet")
      .delete("/v2/pet/12903")
    )
  }

  def updatePet(): ChainBuilder = {
    exec(http("Update a pet")
      .put("/v2/pet")
      .body(RawFileBody("TestData/updatePet.json")).asJson
    )
  }

}
