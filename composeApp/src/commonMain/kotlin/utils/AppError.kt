package utils

data class AppError(
    val error: String,
    val friendlyMessage: String? = null,
    val friendlyResourceMessage: Int? = null
) {
    val stringMessage = friendlyMessage ?: error
}
