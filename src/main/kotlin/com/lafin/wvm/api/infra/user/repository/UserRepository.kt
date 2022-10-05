package com.lafin.wvm.api.infra.user.repository

import com.lafin.wvm.api.infra.user.entity.UserEntity
import com.lafin.wvm.api.shared.data.Password
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
  fun findTopById(id: Long): UserEntity?
  fun findTopByEmail(email: String): UserEntity?
  fun findTopByEmailAndPassword(email: String, password: String): UserEntity?
}