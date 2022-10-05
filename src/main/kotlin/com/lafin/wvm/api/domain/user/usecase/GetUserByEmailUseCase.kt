package com.lafin.wvm.api.domain.user.usecase

import com.lafin.wvm.api.shared.domain.UseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output

interface GetUserByEmailUseCase<I: Input, O: Output>: UseCase<I, O> {
}