package com.lafin.wvm.api.presentation.user.adapter

import com.lafin.wvm.api.domain.user.model.User
import com.lafin.wvm.api.domain.webapp.interactor.GetUserByEmailInput
import com.lafin.wvm.api.domain.webapp.interactor.GetUserByEmailInteractor
import com.lafin.wvm.api.presentation.user.vo.DefaultUserDetails
import com.lafin.wvm.api.shared.data.Email
import com.lafin.wvm.api.shared.presentation.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceAdapter(
  private val interactor: GetUserByEmailInteractor,
) : UserService {

  override fun loadUserByUsername(username: String?): UserDetails {
    username ?: throw UsernameNotFoundException("이메일은 필수입니다.")
    val output = interactor.execute(GetUserByEmailInput(email = Email(username)))
    if (!output.status) {
      throw UsernameNotFoundException(output.message)
    }

    val user = output.user ?: throw UsernameNotFoundException("계정 정보 조회가 실패하였습니다.")
    return DefaultUserDetails(user)
  }
}