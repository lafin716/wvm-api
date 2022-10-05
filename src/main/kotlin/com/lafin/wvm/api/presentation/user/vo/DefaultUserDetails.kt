package com.lafin.wvm.api.presentation.user.vo

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
typealias UserAggregate = com.lafin.wvm.api.domain.user.model.User

class DefaultUserDetails(
  val user: UserAggregate,
) : User(
  user.email.value,
  user.password.value,
  AuthorityUtils.createAuthorityList(user.role.toString()),
) {

  val email: String = user.email.value
}