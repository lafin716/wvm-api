package com.lafin.wvm.api.domain.webapp.usecase

import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output

interface CreateWebAppUseCase<in I: Input, out O: Output> {
  fun create(input: I): O
}