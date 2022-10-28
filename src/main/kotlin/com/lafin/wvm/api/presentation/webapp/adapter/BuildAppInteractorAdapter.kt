package com.lafin.wvm.api.presentation.webapp.adapter

import com.lafin.wvm.api.domain.webapp.interactor.CreateBuildWebAppInteractor
import com.lafin.wvm.api.domain.webapp.usecase.CreateBuildWebAppUseCase
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class BuildAppInteractorAdapter(
  private val createBuildWebAppInteractor: CreateBuildWebAppInteractor,
) {


}