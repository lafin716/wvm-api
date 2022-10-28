package com.lafin.wvm.api.domain.noti.usecase

import com.lafin.wvm.api.shared.domain.UseCase
import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output

interface SubscribeTopicUseCase<I: Input, O: Output>: UseCase<I, O> {
}