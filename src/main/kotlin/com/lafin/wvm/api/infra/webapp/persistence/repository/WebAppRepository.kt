package com.lafin.wvm.api.infra.webapp.persistence.repository

import com.lafin.wvm.api.infra.webapp.persistence.entity.WebAppEntity
import com.lafin.wvm.api.shared.type.AppPlatform
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WebAppRepository : JpaRepository<WebAppEntity, Long>{
  fun findTopByIdAndUserId(id: Long, userId: Long): WebAppEntity?
  fun findTopByUserIdAndNameAndPlatform(userId: Long, name: String, platform: AppPlatform): WebAppEntity?
  fun findAllByUserId(userId: Long): List<WebAppEntity>?
  fun findAllByUserIdOrderByIdDesc(userId: Long, pageable: Pageable): Page<List<WebAppEntity>>??
}