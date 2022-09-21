package com.lafin.wvm.api.shared.infra.entity

import com.lafin.wvm.api.shared.domain.Aggregate

interface Convertable<E: PersistEntity, A: Aggregate> {
  fun toAggregate(entity: E): A
  fun toEntity(aggregate: A): E
}