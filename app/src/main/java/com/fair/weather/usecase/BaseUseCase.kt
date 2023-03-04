package com.fair.weather.usecase

class BaseUseCase {
    interface NoParamUseCase<Result> {
        suspend fun getAction(): Any?
    }

    interface ParamUseCase<Params, Result> {
        suspend fun getAction(params: Params): Any?
    }

    interface ParamWithNoResultsUseCase<Params> {
        suspend fun getAction(params: Params): Any?
    }
}