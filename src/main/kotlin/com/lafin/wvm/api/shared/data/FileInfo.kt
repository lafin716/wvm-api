package com.lafin.wvm.api.shared.data

import org.springframework.util.MimeType

data class FileInfo(
  val name: String,
  val path: String,
  val size: Long,
  val type: MimeType,
)
