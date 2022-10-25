package com.lafin.wvm.api.shared.data

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
) {
    companion object {
        fun parse(version: String): Version {
            val split = version.split(".")
            return Version(
                major = split[0].toInt(),
                minor = split[1].toInt(),
                patch = split[2].toInt(),
            )
        }
    }

    fun updateMajor() = Version(major + 1, 0, 0)

    fun updateMinor() = Version(major, minor + 1, 0)

    fun updatePatch() = Version(major, minor, patch + 1)

    fun toVersionString(): String {
        return "$major.$minor.$patch"
    }
}

