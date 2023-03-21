package com.lafin.wvm.api.infra.webapp.persistence.convert

import com.fasterxml.jackson.databind.util.Converter
import com.lafin.wvm.api.domain.webapp.model.ChangeLog
import com.lafin.wvm.api.infra.webapp.persistence.entity.ChangeLogEntity
import com.lafin.wvm.api.shared.infra.entity.Convertable

class ChangeLogConverter : Convertable<ChangeLogEntity, ChangeLog> {
  override fun toAggregate(entity: ChangeLogEntity): ChangeLog {
    TODO("Not yet implemented")
  }

  override fun toEntity(aggregate: ChangeLog): ChangeLogEntity {
    TODO("Not yet implemented")
  }
}