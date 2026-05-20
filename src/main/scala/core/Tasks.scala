package main.core

import main.domain.task.*

import java.util.UUID

import cats.effect.*
import cats.syntax.all.*
import doobie.util.transactor.Transactor
import doobie.implicits.*
import doobie.postgres.implicits.*

trait Tasks[F[_]]:
    def create(task: Task): F[UUID]
    def all: F[List[Task]]

class TasksLive[F[_]: Concurrent] private (transactor: Transactor[F])
    extends Tasks[F]:
    override def all: F[List[Task]] =
        sql"""
        SELECT
            id,
            title,
            description,
            completed
        FROM tasks
        """.query[Task]
            .stream
            .transact(transactor)
            .compile
            .toList
    override def create(task: Task): F[UUID] =
        sql"""
        INSERT INTO tasks(
            id,
            title,
            description,
            completed
        ) VALUES (
            ${task.id}     
            ${task.title} 
            ${task.description} 
            ${task.completed} 
        )
        """.update
            .withUniqueGeneratedKeys[UUID]("id")
            .transact(transactor)
object TasksLive:
    def make[F[_]: Concurrent](postgres: Transactor[F]): F[TasksLive[F]] =
        TasksLive[F](postgres).pure[F]
