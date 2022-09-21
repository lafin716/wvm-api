package com.lafin.wvm.api.shared.domain

import com.lafin.wvm.api.shared.domain.io.Input
import com.lafin.wvm.api.shared.domain.io.Output

interface UseCase<I: Input, O: Output> {
  fun execute(input: I): O
}