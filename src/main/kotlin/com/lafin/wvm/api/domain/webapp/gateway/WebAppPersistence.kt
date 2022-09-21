package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.shared.domain.gateway.CrudRepository
import com.lafin.wvm.api.shared.domain.gateway.Order

interface WebAppPersistence : CrudRepository<WebApp, WebAppCondition, Order> {
  fun isDuplicateName(condition: WebAppCondition): Boolean
}