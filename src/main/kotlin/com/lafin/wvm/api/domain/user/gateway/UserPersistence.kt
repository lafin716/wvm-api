package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.shared.domain.gateway.CrudRepository
import com.lafin.wvm.api.shared.domain.gateway.Order

interface UserPersistence : CrudRepository<User, UserCondition, Order> {
  fun isDuplicateEmail(condition: UserCondition): Boolean
}