package darfat.dao

import darfat.utils.DB
import darfat.domain.Cart

object CartDAO {

  def insert(cart: Cart): Unit = DB.save(cart)

  def update(cartCode:String, cart: Cart): Unit = {
  	val resCode = DB.query[Cart].whereEqual("cartCode", cartCode).fetchOne().get
  	val cpCart = resCode.copy(productCode = cart.productCode,qty = cart.qty,total = cart.total)
    	DB.save(cpCart)
}
  def retrieveAll(): List[Cart] = DB.query[Cart].fetch().toList

  def retrieveByCode(cartCode:String): List[Cart]= DB.query[Cart].whereEqual("cartCode", cartCode).fetch().toList

  def deleteByCode(cartCode:String) :Unit = {
  	val result = DB.query[Cart].whereEqual("cartCode", cartCode).fetch().toList
  	println("delete by cartCode!") 
  	val task = DB.transaction {
  	      for (p <- result)
	        DB.delete(p)
	    }
	task

  }


}