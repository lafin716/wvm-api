package com.lafin.wvm.api.shared.presentation.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "file")
@ConstructorBinding
data class FileProperty(
  val upload: UploadProperty,
) {
  data class UploadProperty(
    val location: String,
  )
}
