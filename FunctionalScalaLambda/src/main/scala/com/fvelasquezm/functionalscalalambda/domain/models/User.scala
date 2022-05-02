package com.fvelasquezm.functionalscalalambda.domain.models

import scala.util.control.NonFatal

//type UserType = "FREE" | "PAYING"
final case class User(id: String, userType: String, phone: String)

object User{
    def apply(values: Map[String, String]): User = User(values("id"), values("userType"), values("phone"))
}