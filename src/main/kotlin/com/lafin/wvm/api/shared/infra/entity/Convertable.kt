package com.lafin.wvm.api.shared.infra.entity

interface Convertable<E: PersistEntity, A: Any> {
  fun toAggregate(entity: E): A
  fun toEntity(aggregate: A): E
}