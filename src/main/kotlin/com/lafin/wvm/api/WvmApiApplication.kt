package com.lafin.wvm.api

import com.lafin.wvm.api.shared.presentation.property.FileProperty
import com.lafin.wvm.api.shared.presentation.property.SecurityProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperty::class, FileProperty::class)
class WvmApiApplication

fun main(args: Array<String>) {
  runApplication<WvmApiApplication>(*args)
}
