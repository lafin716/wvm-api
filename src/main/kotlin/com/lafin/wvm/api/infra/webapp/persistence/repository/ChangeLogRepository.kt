package com.lafin.wvm.api.infra.webapp.persistence.repository

import com.lafin.wvm.api.infra.webapp.persistence.entity.ChangeLogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChangeLogRepository : JpaRepository<ChangeLogEntity, Long> {
  fun findAllByAppId(appId: Long, pageable: Pageable): Page<ChangeLogEntity>?
}