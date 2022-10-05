package com.lafin.wvm.api.infra.user.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.gateway.UserCondition
import com.lafin.wvm.api.infra.user.convert.UserConverter
import com.lafin.wvm.api.infra.user.repository.UserRepository
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.data.Password
import com.lafin.wvm.api.shared.status.UserStatus
import com.lafin.wvm.api.shared.type.RoleType
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
@DisplayName("유저_퍼시스턴스_어댑터")
class UserPersistenceAdapterTest (
  @Autowired repository: UserRepository,
) {

  val converter: UserConverter = UserConverter()

  @InjectMocks
  val adapter: UserPersistenceAdapter = UserPersistenceAdapter(repository, converter)

  @Test
  fun 유저_신규추가() {
    val savedUser = adapter.save(createUser())
    println(savedUser)
    assertNotNull(savedUser)
    assertTrue((savedUser!!.id ?: 0L) > 0L)
  }

  @Test
  fun 유저_아이디_조회() {
    adapter.save(createUser())
    val user = adapter.getOne(UserCondition(
      id = 1L,
    ))

    println(user)
    assertNotNull(user)
  }

  @Test
  fun 유저_이메일_조회() {
    adapter.save(createUser())
    val user = adapter.getOne(UserCondition(
      email = Email("lafin@gmail.com"),
    ))

    println(user)
    assertNotNull(user)
  }

  private fun createUser() : User {
    return User(
      name = "재욱",
      email = Email("lafin@gmail.com"),
      password = Password("lafin1234"),
      role = RoleType.USER,
      status = UserStatus.ACTIVE,
      createdAt = LocalDateTime.now(),
      updatedAt = LocalDateTime.now(),
      removedAt = null,
      bannedAt = null,
      lastLoggedIn = null,
    )
  }
}