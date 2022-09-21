package com.lafin.wvm.api.infra.webapp.persistence.repository

import com.lafin.wvm.api.infra.webapp.persistence.entity.WebAppEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WebAppRepository : JpaRepository<WebAppEntity, Long>{
  fun findTopByIdAndUserId(id: Long, userId: Long): WebAppEntity?
  fun findTopByUserIdAndName(userId: Long, name: String): WebAppEntity?
  fun findAllByUserId(userId: Long): List<WebAppEntity>?
}