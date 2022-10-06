package com.lafin.wvm.api.shared.presentation

import com.lafin.wvm.api.shared.data.ValidException
import com.lafin.wvm.api.shared.presentation.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

  @ExceptionHandler(Exception::class)
  fun exception(e: Exception): ResponseEntity<ErrorResponse> {
    e.printStackTrace()
    return ResponseEntity.badRequest().body(ErrorResponse(
      message = e.message ?: "일시적인 오류가 발생하였습니다."
    ))
  }

  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun validException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
    return ResponseEntity.badRequest().body(ErrorResponse(
      message = "필수 파라미터가 없습니다."
    ))
  }

  @ExceptionHandler(ValidException::class)
  fun validException(e: ValidException): ResponseEntity<ErrorResponse> {
    return ResponseEntity.badRequest().body(ErrorResponse(
      message = e.message
    ))
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
  fun mediaTypeException(e: HttpMediaTypeNotSupportedException): ResponseEntity<ErrorResponse> {
    return ResponseEntity.badRequest().body(ErrorResponse(
      message = "지원하는 형식의 데이터가 아닙니다. (application/json)"
    ))
  }
}