package darfat.utils

import sorm._
import darfat.domain._

object DB extends Instance(
  //entities = Set(Entity[Product](unique = Set() + Seq("code"))  ,  Entity[Cart](unique = Set() + Seq("cartCode") + Seq("productCode")  )  ),
  entities = Set() + Entity[Product]() + Entity[Cart](),
  url = "jdbc:h2:mem:test",
  user = "",
  password = "",
  initMode = InitMode.Create,
  poolSize = 12
)