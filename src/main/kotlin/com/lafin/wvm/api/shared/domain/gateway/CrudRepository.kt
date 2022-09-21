package com.lafin.wvm.api.shared.domain.gateway

import com.lafin.wvm.api.shared.domain.Aggregate

interface CrudRepository<T: Aggregate, in C: Condition, in O: Order> {
  fun save(aggregate: T): T?
  fun delete(condition: C): Boolean
  fun getList(): List<T>?
  fun getList(condition: C): List<T>?
  fun getList(condition: C, order: O): List<T>?
  fun getOne(condition: C): T?
}