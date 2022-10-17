package com.lafin.wvm.api.domain.webapp.gateway

import com.lafin.wvm.api.shared.data.FileInfo
import org.springframework.web.multipart.MultipartFile

interface WebAppStorage {
  fun upload(part: MultipartFile?): FileInfo
}