package com.fvelasquezm.functionalscalalambda.domain.traits

import cats.effect.IO
import com.fvelasquezm.functionalscalalambda.domain.models.{SearchFilter, User}


trait Repository {
  def getUser(sf: SearchFilter): IO[User]
}
