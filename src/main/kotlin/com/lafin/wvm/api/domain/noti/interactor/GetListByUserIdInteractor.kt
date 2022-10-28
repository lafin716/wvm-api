package com.lafin.wvm.api.domain.noti.interactor

import com.lafin.wvm.api.domain.noti.gateway.NotificationRepository
import com.lafin.wvm.api.domain.noti.usecase.GetListByUserIdUseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.type.AppPlatform
import com.lafin.wvm.api.shared.type.AppTheme
import com.lafin.wvm.api.shared.type.LicenseType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class GetListByUserIdInteractor : GetListByUserIdUseCase<GetListByUserIdInput, GetListByUserIdOutput> {
  override fun execute(input: GetListByUserIdInput): GetListByUserIdOutput {


    return GetListByUserIdOutput(true)
  }
}

data class GetListByUserIdInput(
  val userId: Long,
  val name: String,
  val initUrl: String,
  val icon: MultipartFile?,
  val splash: MultipartFile?,
  val theme: AppTheme,
  val platform: AppPlatform,
  val licenseType: LicenseType,
) : Input {
  override fun validate(): Boolean {
    return true
  }
}

data class GetListByUserIdOutput(
  val status: Boolean,
  val message: String = "",
) : Output