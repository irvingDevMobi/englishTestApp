package utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<ResultOf<T>>.mapSuccess(
    crossinline transform: suspend (T) -> R
): Flow<ResultOf<R>> = map { result ->
    when (result) {
        is ResultOf.Success -> ResultOf.Success(transform(result.value), result.isUpdating)
        is ResultOf.Failure -> result
    }
}
