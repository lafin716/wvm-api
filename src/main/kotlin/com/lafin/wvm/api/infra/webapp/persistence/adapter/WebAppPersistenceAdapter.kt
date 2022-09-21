package com.lafin.wvm.api.infra.webapp.persistence.adapter

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.domain.webapp.gateway.WebAppPersistence
import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.infra.webapp.persistence.convert.WebAppConverter
import com.lafin.wvm.api.infra.webapp.persistence.repository.WebAppRepository
import com.lafin.wvm.api.shared.domain.gateway.Order
import org.springframework.stereotype.Component

@Component
class WebAppPersistenceAdapter(
  val repository: WebAppRepository,
  val webAppConverter: WebAppConverter,
) : WebAppPersistence {

  override fun isDuplicateName(condition: WebAppCondition): Boolean {
    condition.userId ?: return false
    condition.name ?: return false

    return repository.findTopByUserIdAndName(
      userId = condition.userId,
      name = condition.name,
    ) != null
  }

  override fun save(aggregate: WebApp): WebApp? {
    val savedEntity = repository.save(webAppConverter.toEntity(aggregate))
    return webAppConverter.toAggregate(savedEntity)
  }

  override fun delete(condition: WebAppCondition): Boolean {
    condition.id ?: return false
    condition.userId ?: return false
    repository.findTopByIdAndUserId(condition.id, condition.userId) ?: return false

    repository.deleteById(condition.id)
    return true
  }

  override fun getList(): List<WebApp>? {
    TODO("Not yet implemented")
  }

  override fun getList(condition: WebAppCondition): List<WebApp>? {
    condition.userId ?: return listOf()
    val webApps = repository.findAllByUserId(condition.userId) ?: return listOf()
    return webApps
      .map { webApp -> webAppConverter.toAggregate(webApp)}
      .toList()
  }

  override fun getList(condition: WebAppCondition, order: Order): List<WebApp>? {
    TODO("Not yet implemented")
  }

  override fun getOne(condition: WebAppCondition): WebApp? {
    condition.id ?: return null
    condition.userId ?: return null
    val webApp = repository.findTopByIdAndUserId(condition.id, condition.userId) ?: return null
    return webAppConverter.toAggregate(webApp)
  }
}