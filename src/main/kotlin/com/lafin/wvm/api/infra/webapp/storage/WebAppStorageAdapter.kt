package com.lafin.wvm.api.infra.webapp.storage

import com.lafin.wvm.api.domain.webapp.gateway.WebAppStorage
import com.lafin.wvm.api.shared.data.FileError
import com.lafin.wvm.api.shared.data.FileInfo
import com.lafin.wvm.api.shared.property.FileProperty
import org.springframework.stereotype.Component
import org.springframework.util.MimeType
import org.springframework.web.multipart.MultipartFile

@Component
class WebAppStorageAdapter(
  val uploader: FileUploader,
  val fileProperty: FileProperty,
) : WebAppStorage {
  override fun upload(part: MultipartFile?): FileInfo {
    if (part == null || part.originalFilename == "" || part.size == 0L) {
      return FileInfo(
        name = "",
        path = "",
        url = "",
        size = 0L,
        error = FileError(
          status = true,
          message = "업로드 할 파일이 없습니다.",
        )
      )
    }
    val domain = fileProperty.upload.baseDomain
    val path = fileProperty.upload.location
    val fileName = uploader.upload(path, part)

    return FileInfo(
      name = fileName,
      path = "${domain}/${path}",
      url = "${domain}/${fileName}",
      size = part.size,
      type = part.contentType,
    )
  }
}