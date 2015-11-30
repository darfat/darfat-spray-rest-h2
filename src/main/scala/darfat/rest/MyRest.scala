package darfat.rest

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import darfat.domain.CartJsonProtocol._
import darfat.domain.Cart
import darfat.dao._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyRestActor extends Actor with MyRest {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(getRoute ~ setRoute ~ getRouteByCode)
}


// this trait defines our service behavior independently from the service actor
trait MyRest extends HttpService {

  val getRoute =
    path("cart") {
      get {
        respondWithMediaType(`application/json`) {
          complete(CartDAO.retrieveAll())
        }
      }
    }

  val getRouteByCode =
    path("cart" / Segment) {
       cartCode =>
        get {
          respondWithMediaType(`application/json`) {
            complete(CartDAO.retrieveByCode(cartCode))
          }
        }~
        delete {
          respondWithMediaType(`application/json`) {
            complete(
                CartDAO.deleteByCode(cartCode),
                """{"status" : "success"}"""
              )
          }
        }~
        put {
          respondWithMediaType(`application/json`) {
            entity(as[Cart]) { cart => CartDAO.update(cartCode,cart)
                complete("""{"status" : "success"}""")
            }
          }
        }
    }
 

  val setRoute =
    path("cart") {
      post {
        respondWithMediaType(`application/json`) {
          entity(as[Cart]) { cart => CartDAO.insert(cart)
            complete("""{"status" : "success"}""")
          }
        }
      }
    }


}