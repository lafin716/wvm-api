package com.lafin.wvm.api.domain.webapp.usecase

import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output
import com.lafin.wvm.api.shared.domain.UseCase

interface ExpiredWebAppUseCase<I: Input, O: Output>: UseCase<I, O> {
}