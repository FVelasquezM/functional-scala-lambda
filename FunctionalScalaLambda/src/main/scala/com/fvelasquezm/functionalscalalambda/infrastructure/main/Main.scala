package com.fvelasquezm.functionalscalalambda.infrastructure.main

import cats.effect.{IO, IOApp}
import cats.effect.unsafe.implicits.global
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.{APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse}
import com.fvelasquezm.functionalscalalambda.application.UserFunctions
import com.fvelasquezm.functionalscalalambda.domain.models.{SearchFilter, User}
import com.fvelasquezm.functionalscalalambda.infrastructure.db.DynamoRepository
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.{AttributeValue, GetItemRequest}

import scala.jdk.CollectionConverters._

object Main{

  private def constructHttpResponse(user: User): IO[APIGatewayV2HTTPResponse] =
    IO{
      APIGatewayV2HTTPResponse
        .builder()
        .withStatusCode(200)
        .withBody(user.toString)
        .build()
    }

  def handler(event: java.util.Map[String, String], context: Context): APIGatewayV2HTTPResponse =

    //Define dependencies
    val repository = DynamoRepository

    val program: IO[APIGatewayV2HTTPResponse] =
      for
        //.run injects the Repository dependency using a Reader Monad
        id <- IO {SearchFilter(event.asScala("userId"))}
        user <- UserFunctions.getUser(id).run(repository)
        response <- constructHttpResponse(user)
      yield response

    program.unsafeRunSync()
}
