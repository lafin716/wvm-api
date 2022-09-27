package com.lafin.wvm.api.shared.domain.gateway

import com.lafin.wvm.api.shared.domain.Aggregate
import org.springframework.data.domain.Page

interface CrudRepository<T: Aggregate, in C: Condition, in O: Order> {
  fun save(aggregate: T): T?
  fun delete(condition: C): Boolean
  fun getList(condition: C): Page<List<T>>
  fun getList(condition: C, order: O): Page<List<T>>
  fun getOne(condition: C): T?
}