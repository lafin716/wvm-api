package com.lafin.wvm.api.shared.presentation.response

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import javax.servlet.http.HttpServletResponse

fun responseError(
  response: HttpServletResponse,
  message: String = "",
  status: HttpStatus = HttpStatus.UNAUTHORIZED,
) {
  val errorResponse = ErrorResponse(message = message)
  response.status = status.value()
  response.contentType = MediaType.APPLICATION_JSON_VALUE
  response.characterEncoding = "UTF-8"
  response.getWriter().write(convertObjectToJson(errorResponse))
}

fun convertObjectToJson(obj: Any): String {
  val mapper = ObjectMapper()
  return mapper.writeValueAsString(obj)
}