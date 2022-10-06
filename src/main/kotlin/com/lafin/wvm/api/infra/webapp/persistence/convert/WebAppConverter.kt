package com.lafin.wvm.api.infra.webapp.persistence.convert

import com.lafin.wvm.api.domain.webapp.model.WebApp
import com.lafin.wvm.api.infra.webapp.persistence.entity.WebAppEntity
import com.lafin.wvm.api.shared.infra.entity.Convertable
import org.springframework.stereotype.Component

@Component
class WebAppConverter : Convertable<WebAppEntity, WebApp> {

  override fun toAggregate(entity: WebAppEntity): WebApp {
    return WebApp(
      id = entity.id,
      userId = entity.userId,
      name = entity.name,
      initUrl = entity.initUrl,
      theme = entity.theme,
      platform = entity.platform,
      licenseType = entity.licenseType,
      status = entity.status,
      buildStatus = entity.buildStatus,
      deployStatus = entity.deployStatus,
      createdAt = entity.createdAt,
      updatedAt = entity.updatedAt,
      deletedAt = entity.deletedAt,
      lastBuiltAt = entity.lastBuiltAt,
      lastDeployedAt = entity.lastDeployedAt,
    )
  }

  override fun toEntity(aggregate: WebApp): WebAppEntity {
    return WebAppEntity(
      id = aggregate.id,
      userId = aggregate.userId,
      name = aggregate.name,
      initUrl = aggregate.initUrl,
      theme = aggregate.theme,
      platform = aggregate.platform,
      licenseType = aggregate.licenseType,
      status = aggregate.status,
      buildStatus = aggregate.buildStatus,
      deployStatus = aggregate.deployStatus,
      createdAt = aggregate.createdAt,
      updatedAt = aggregate.updatedAt,
      deletedAt = aggregate.deletedAt,
      lastBuiltAt = aggregate.lastBuiltAt,
      lastDeployedAt = aggregate.lastDeployedAt,
    )
  }
}