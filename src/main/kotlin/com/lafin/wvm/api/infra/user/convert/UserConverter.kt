package com.lafin.wvm.api.infra.user.convert

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.infra.user.entity.UserEntity
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.infra.entity.Convertable
import org.springframework.stereotype.Component

@Component
class UserConverter : Convertable<UserEntity, User> {
  override fun toAggregate(entity: UserEntity): User {
    return User(
      id = entity.id,
      name = entity.name,
      email = Email(entity.email),
      password = Password(entity.password),
      role = entity.role,
      status = entity.status,
      createdAt = entity.createdAt,
      updatedAt = entity.updatedAt,
      removedAt = entity.removedAt,
      bannedAt = entity.bannedAt,
      lastLoggedIn = entity.lastLoggedIn,
    )
  }

  override fun toEntity(aggregate: User): UserEntity {
    return UserEntity(
      name = aggregate.name,
      email = aggregate.email.value,
      password = aggregate.password.value,
      role = aggregate.role,
      status = aggregate.status,
      createdAt = aggregate.createdAt,
      updatedAt = aggregate.updatedAt,
      removedAt = aggregate.removedAt,
      bannedAt = aggregate.bannedAt,
      lastLoggedIn = aggregate.lastLoggedIn,
    )
  }
}