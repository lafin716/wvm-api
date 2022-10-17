package com.lafin.wvm.api.infra.webapp.storage

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class FileUploader {

  fun upload(basePath: String, file: MultipartFile): String {
    val fileName = file.originalFilename
    val newFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
    val ext = getExt(fileName)

    try {
      file.transferTo(File("${basePath}/${newFileName}.${ext}"))
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
    return "${newFileName}.${ext}"
  }

  fun getExt(fileName: String?): String {
    val lastIndex = fileName?.lastIndexOf('.') ?: throw NullPointerException("파일 확장자가 없습니다.")
    return fileName.substring(lastIndex, fileName.length)
  }
}