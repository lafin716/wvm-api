package com.lafin.wvm.api.shared.data

data class FileInfo(
  val name: String,
  val path: String,
  val url: String,
  val size: Long,
  val type: String? = null,
  val width: Int? = null,
  val height: Int? = null,
  val error: FileError? = null,
)

data class FileError(
  val status: Boolean = false,
  val message: String = "",
) {

}
