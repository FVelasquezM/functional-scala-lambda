package com.fvelasquezm.functionalscalalambda.infrastructure.db

import cats.effect.IO
import com.fvelasquezm.functionalscalalambda.domain.traits.Repository
import com.fvelasquezm.functionalscalalambda.domain.models.{SearchFilter, User}
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.{AttributeValue, GetItemRequest}
import scala.jdk.CollectionConverters._

object DynamoRepository extends Repository {
  private lazy val ddb = DynamoDbClient.builder().build()

  def getUser(sf: SearchFilter): IO[User] =
    IO {
      val item = ddb.getItem(
        GetItemRequest.builder()
          .tableName("users")
          .key(Map(
            "id" -> AttributeValue.builder().s(sf.id).build()
          ).asJava)
          .build()
      ).item.asScala

      User(item.map((x: String, y: AttributeValue) => (x, y.s)).toMap)
    }

  //def saveUser(user: User): [Unit] = ???
}
