package teka.mobile.shoppingassistant.ui.add_edit_todo

sealed class AddEditTodoEvent {
    //what events could happen here

    //1. change the text content of title
    data class OnTitleChange(val title: String): AddEditTodoEvent()
    //2. change the text content of description
    data class OnDescriptionChange(val description: String): AddEditTodoEvent()

    object OnSaveTodoClick: AddEditTodoEvent()

}
