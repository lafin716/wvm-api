package com.lafin.wvm.api.infra.user.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.infra.user.convert.UserConverter
import com.lafin.wvm.api.infra.user.repository.UserRepository
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.domain.gateway.Order
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
  val repository: UserRepository,
  val userConverter: UserConverter,
) : UserPersistence {

  override fun isDuplicateEmail(condition: UserCondition): Boolean {
    condition.email?.value ?: return false
    return repository.findTopByEmail(condition.email.value)?.let { userEntity -> true } ?: false
  }

  override fun getByEmailAndPassword(email: Email, password: Password): User? {
    return repository.findTopByEmailAndPassword(email.value, password.value)?.let {
      userConverter.toAggregate(it)
    }
  }

  override fun save(aggregate: User): User? {
    return userConverter.toAggregate(repository.save(userConverter.toEntity(aggregate)))
  }

  override fun delete(condition: UserCondition): Boolean {
    TODO("Not yet implemented")
  }

  override fun getList(condition: UserCondition): List<User> {
    TODO("Not yet implemented")
  }

  override fun getList(condition: UserCondition, order: Order): List<User> {
    TODO("Not yet implemented")
  }

  override fun getOne(condition: UserCondition): User? {
    return if (condition.id != null && condition.id > 0L) {
      repository.findTopById(condition.id)?.let {
        userConverter.toAggregate(it)
      }
    } else if (condition.email != null) {
      repository.findTopByEmail(condition.email.value)?.let {
        userConverter.toAggregate(it)
      }
    } else {
      throw ClassNotFoundException("test")
    }
  }
}