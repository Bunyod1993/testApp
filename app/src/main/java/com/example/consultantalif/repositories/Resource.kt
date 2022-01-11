package com.example.consultantalif.repositories

sealed class Resource<Type>(
    val model: Type? = null,
    val message: String? = null,
    val code: Int = 0
) {
    class Default : Resource<Any>()
    class Success<Type>(model: Type) : Resource<Type>(model = model)
    class Loading<Type> : Resource<Type>()
    class Error<Type>(message: String, code: Int = -1) :
        Resource<Type>(message = message, code = code)

    class Empty<Type> : Resource<Type>()
}