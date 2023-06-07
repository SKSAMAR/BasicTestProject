package sk.samar.testproject.domain.model


data class ScreenState<T>(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: T? = null
)