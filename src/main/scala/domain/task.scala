package main.domain

object task:
    case class Task(
        id: String,
        title: String,
        description: String,
        completed: Boolean
    )
