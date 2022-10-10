package com.lafin.wvm.api.infra.webapp.storage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile

internal class FileUploaderTest {

  @Test
  fun 파일_업로드() {
    val uploader = FileUploader()
    val path = "/User/Documents/test"
    val file = MockMultipartFile(
      "file",
      "hello.txt",
      MediaType.TEXT_PLAIN_VALUE,
      "Hello, World!".toByteArray()
    )

    uploader.upload(path, file)
  }
}