package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.domain.webapp.model.WebApp

interface WebAppRepository {
  fun save(webApp: WebApp): WebApp
  fun isDuplicateName(name: String): Boolean
  fun getOne(id: Long): WebApp?
  fun getList(): List<WebApp>?
  fun delete(id: Long): Boolean
}