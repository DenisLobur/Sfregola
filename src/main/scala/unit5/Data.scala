package unit5
import Lixt.*

object Data {  

  case class ContactNumber(number: String, label: Label)

  case class Contact(
                      name: String,
                      surname: String,
                      numbers: Lixt[ContactNumber],
                      company: Option[String],
                      email: Option[String]
                    )
}
