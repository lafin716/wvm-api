package com.lafin.wvm.api.presentation.webapp.adapter

import com.lafin.wvm.api.domain.webapp.interactor.*
import org.springframework.stereotype.Service

@Service
class WebAppInteractorAdapter(
  private val createWebAppInteractor: CreateWebAppInteractor,
  private val updateWebAppInteractor: UpdateWebAppInteractor,
  private val deleteWebAppInteractor: DeleteWebAppInteractor,
  private val getListWebAppInteractor: GetListWebAppInteractor,
  private val getWebAppInteractor: GetWebAppInteractor,
) {

}