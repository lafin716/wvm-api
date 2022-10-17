package com.lafin.wvm.api.shared.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "file")
@ConstructorBinding
data class FileProperty(
  val upload: UploadProperty,
) {
  data class UploadProperty(
    val baseDomain: String,
    val location: String,
  )
}
