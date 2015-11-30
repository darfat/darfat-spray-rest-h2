package darfat.dao

import darfat.utils.DB
import darfat.domain.Product

object ProductDAO {

  def insert(product: Product): Unit = DB.save(product)

  def update(code:String, product: Product): Unit = {
  	val resCode = DB.query[Product].whereEqual("code", code).fetchOne().get
  	val cpProduct = resCode.copy(name = product.name,price = product.price)
    	DB.save(cpProduct)
}
  def retrieveAll(): List[Product] = DB.query[Product].fetch().toList

  def retrieveByCode(code:String): List[Product]= DB.query[Product].whereEqual("code", code).fetch().toList

  def deleteByCode(code:String) :Unit = {
  	val result = DB.query[Product].whereEqual("code", code).fetch().toList
  	println("delete by code!") 
  	val task = DB.transaction {
  	      println("delete in transaction") 
	      for (p <- result)
	        DB.delete(p)
	          println("delete d ") 
	    }
	task

  }


}