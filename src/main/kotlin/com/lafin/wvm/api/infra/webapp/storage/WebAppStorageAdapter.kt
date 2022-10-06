package com.lafin.wvm.api.infra.webapp.storage

import com.lafin.wvm.api.domain.webapp.gateway.WebAppStorage
import com.lafin.wvm.api.shared.data.FileInfo
import org.springframework.util.MimeType
import org.springframework.web.multipart.MultipartFile

class WebAppStorageAdapter : WebAppStorage {
  override fun upload(part: MultipartFile): FileInfo {
    return FileInfo(
      name = "",
      path = "",
      size = 0,
      type = MimeType("image/png"),
    )
  }
}