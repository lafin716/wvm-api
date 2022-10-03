package com.lafin.wvm.api.infra.user.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.domain.webapp.gateway.UserPersistence
import com.lafin.wvm.api.shared.domain.gateway.Order
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter : UserPersistence {

  override fun isDuplicateEmail(condition: UserCondition): Boolean {
    TODO("Not yet implemented")
  }

  override fun save(aggregate: User): User? {
    TODO("Not yet implemented")
  }

  override fun delete(condition: UserCondition): Boolean {
    TODO("Not yet implemented")
  }

  override fun getList(condition: UserCondition): Page<User> {
    TODO("Not yet implemented")
  }

  override fun getList(condition: UserCondition, order: Order): Page<User> {
    TODO("Not yet implemented")
  }

  override fun getOne(condition: UserCondition): User? {
    TODO("Not yet implemented")
  }
}