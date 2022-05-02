package com.fvelasquezm.functionalscalalambda.application

import cats.effect.IO
import cats.data.Reader
import com.fvelasquezm.functionalscalalambda.domain.models.{SearchFilter, User}
import com.fvelasquezm.functionalscalalambda.domain.traits.Repository

object UserFunctions {

  def getUser(sf: SearchFilter): Reader[Repository, IO[User]] =
    Reader(
      (repository: Repository) =>
        repository.getUser(sf)
    )

}
