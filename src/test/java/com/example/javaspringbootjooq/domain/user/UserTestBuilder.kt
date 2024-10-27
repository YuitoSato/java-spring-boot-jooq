package com.example.javaspringbootjooq.domain.user

class UserTestBuilder {
  private var id: Int = 1
  private var name: String = "name"
  private var email: String = "email@example.com"

  fun id(id: Int): UserTestBuilder {
    this.id = id
    return this
  }

  fun name(name: String): UserTestBuilder {
    this.name = name
    return this
  }

  fun email(email: String): UserTestBuilder {
    this.email = email
    return this
  }

  fun build(): User {
    return User(id, name, email)
  }
}
