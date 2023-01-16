package teka.mobile.shoppingassistant.util

sealed class UiEvent {
    //These are events that run on our UI layer
    //that we want to send from our View Model.
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null,
    ):UiEvent()
}
