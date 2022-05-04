package com.fvelasquezm.functionalscalalambda.helpers

implicit class Map2Class(values: Map[String,String]){
  def convert[A](implicit mapper: MapConvert[A]) = mapper conv (values)
}

trait MapConvert[A]{
  def conv(values: Map[String,String]): A
}

