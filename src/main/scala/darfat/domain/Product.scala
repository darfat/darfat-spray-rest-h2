package darfat.domain

import spray.httpx.SprayJsonSupport
import spray.json._

case class Product(code: String,name: String, price: Int)

object ProductJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{

  implicit object ProductJsonFormat extends RootJsonFormat[Product] {
    def write(p: Product) = JsObject(
      "code" -> JsString(p.code),
      "name" -> JsString(p.name),
      "price" -> JsNumber(p.price)
    )

    def read(value: JsValue) = {
      value.asJsObject.getFields("code","name", "price") match {
        case Seq(JsString(code),JsString(name), JsNumber(price)) =>
          new Product(code,name, price.toInt)
        case _ => throw new DeserializationException("Product expected")
      }
    }
  }
}