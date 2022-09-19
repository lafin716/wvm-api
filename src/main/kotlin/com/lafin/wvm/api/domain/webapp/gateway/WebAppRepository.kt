package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface WebAppRepository {
  fun save(webApp: WebApp): WebApp
  fun isDuplicateName(name: String): Boolean
}