package com.fvelasquezm.functionalscalalambda.domain.models

import com.fvelasquezm.functionalscalalambda.helpers.MapConvert

import scala.util.control.NonFatal

//type UserType = "FREE" | "PAYING"
final case class User(id: String, userType: String, phone: String)

object User{
    implicit val mapConvert: MapConvert[User] = new MapConvert[User]{
        def conv(values: Map[String, String]) = User(values("id"), values("userType"), values("phone"))
    }
}