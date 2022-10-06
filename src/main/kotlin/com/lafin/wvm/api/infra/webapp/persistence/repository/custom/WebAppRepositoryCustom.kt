package com.lafin.wvm.api.infra.webapp.persistence.repository.custom

import com.lafin.wvm.api.domain.webapp.gateway.WebAppCondition
import com.lafin.wvm.api.infra.webapp.persistence.entity.QWebAppEntity
import com.lafin.wvm.api.infra.webapp.persistence.entity.WebAppEntity
import com.lafin.wvm.api.shared.type.AppPlatform
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class WebAppRepositoryCustom(
  private val jpaQueryFactory: JPAQueryFactory
) {

  val app = QWebAppEntity("app")

  fun getList(condition: WebAppCondition): List<WebAppEntity>? {
    val paging = PageRequest.of(condition.page, condition.size)
    return jpaQueryFactory
      .select(app)
      .from(app)
      .where(
        equalUserId(condition.userId),
        equalPlatform(condition.platform),
      )
      .offset(paging.offset)
      .limit(paging.pageSize.toLong())
      .orderBy(app.id.desc())
      .fetch()
  }

  fun equalUserId(userId: Long?): BooleanExpression? {
    userId ?: throw Exception("필수 조건값이 누락되었습니다.")
    return app.userId.eq(userId) ?: null
  }

  fun equalPlatform(platform: AppPlatform?): BooleanExpression? {
    platform ?: return null
    return app.platform.eq(platform)
  }

}