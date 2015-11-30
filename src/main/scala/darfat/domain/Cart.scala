package darfat.domain

import spray.httpx.SprayJsonSupport
import spray.json._

case class Cart(cartCode: String,productCode: String,qty: Int, total: Int)

object CartJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{

  implicit object CartJsonFormat extends RootJsonFormat[Cart] {
    def write(p: Cart) = JsObject(
       "cartCode" -> JsString(p.cartCode),
      "productCode" -> JsString(p.productCode),
      "qty" -> JsNumber(p.qty),
      "total" -> JsNumber(p.total)
    )

    def read(value: JsValue) = {
      value.asJsObject.getFields("cartCode","productCode","qty", "total") match {
        case Seq(JsString(cartCode),JsString(productCode),JsNumber(qty), JsNumber(total)) =>
          new Cart(cartCode,productCode,qty.toInt, total.toInt)
        case _ => throw new DeserializationException("Cart expected")
      }
    }
  }
}