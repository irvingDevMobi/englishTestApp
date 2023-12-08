package utils

sealed class ResultOf<out T> {
    data class Success<out R>(
        val value: R,
        val isUpdating: Boolean = false
    ) : ResultOf<R>()

    data class Failure(
        val appError: AppError
    ) : ResultOf<Nothing>()

    companion object {
        fun <T> failure(
            error: String,
            friendlyMessage: String? = null,
            friendlyResourceMessage: Int? = null
        ): ResultOf<T> = Failure(AppError(error, friendlyMessage, friendlyResourceMessage))
    }
}

inline fun <reified T> ResultOf<T>.onSuccess(callback: (value: T) -> Unit): ResultOf<T> {
    if (this is ResultOf.Success) {
        callback(value)
    }
    return this
}

inline fun <reified T> ResultOf<T>.onSuccess(
    callback: (value: T, isUpdating: Boolean) -> Unit
): ResultOf<T> {
    if (this is ResultOf.Success) {
        callback(value, isUpdating)
    }
    return this
}

inline fun <reified T> ResultOf<T>.onFailure(
    callback: (appError: AppError?) -> Unit
): ResultOf<T> {
    if (this is ResultOf.Failure) {
        callback(appError)
    }
    return this
}

inline fun <reified T, reified R> ResultOf<T>.map(transform: (T, Boolean) -> R): ResultOf<R> {
    return when (this) {
        is ResultOf.Success -> ResultOf.Success(transform(value, isUpdating))
        is ResultOf.Failure -> this
    }
}

inline fun <T> ResultOf<T>.withDefault(value: () -> T): ResultOf.Success<T> {
    return when (this) {
        is ResultOf.Success -> this
        is ResultOf.Failure -> ResultOf.Success(value())
    }
}
