package com.lafin.wvm.api.shared.presentation.resolver

import com.fasterxml.jackson.databind.ObjectMapper
import com.lafin.wvm.api.shared.presentation.annotation.QueryStringResolver
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

@Component
class QueryStringArgsResolver : HandlerMethodArgumentResolver {

  override fun supportsParameter(parameter: MethodParameter): Boolean {
    val type = parameter.getParameterAnnotation(QueryStringResolver::class.java)
    return type != null
  }

  override fun resolveArgument(
    parameter: MethodParameter,
    mavContainer: ModelAndViewContainer?,
    webRequest: NativeWebRequest,
    binderFactory: WebDataBinderFactory?
  ): Any? {
    val mapper = ObjectMapper()
    val request = webRequest.getNativeRequest() as HttpServletRequest
    val json = toJson(request.getQueryString())
    val obj = mapper.readValue(json, parameter.getParameterType())

    return obj
  }

  private fun toJson(a: String): String? {
    var res = "{\""
    for (i in 0 until a.length) {
      if (a[i] == '=') {
        res += "\"" + ":" + "\""
      } else if (a[i] == '&') {
        res += "\"" + "," + "\""
      } else {
        res += a[i]
      }
    }
    res += "\"" + "}"
    return res
  }
}