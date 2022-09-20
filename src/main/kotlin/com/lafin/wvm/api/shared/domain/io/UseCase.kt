package com.lafin.wvm.api.shared.domain.io

interface UseCase<I: Input, O: Output> {
  fun execute(input: I): O
}