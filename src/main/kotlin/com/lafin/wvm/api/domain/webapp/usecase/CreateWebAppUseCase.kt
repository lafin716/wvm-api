package com.lafin.wvm.api.domain.webapp.usecase

import com.lafin.wvm.api.domain.Input
import com.lafin.wvm.api.domain.Output

interface CreateWebAppUseCase {
  fun create(input: Input): Output
}