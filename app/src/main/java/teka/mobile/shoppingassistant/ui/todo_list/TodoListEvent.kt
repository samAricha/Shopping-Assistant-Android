package teka.mobile.shoppingassistant.ui.todo_list

import teka.mobile.shoppingassistant.data.Todo

//this is for any event that happens on the screen i.e
//TodoList screen N/B: Do this for every single screen
sealed class TodoListEvent{
    //what kind of user interactions can we do
    //in the todoList screen.

    data class OnDeleteTodoClick(val todo: Todo): TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    data class OnTodoClick(val todo: Todo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()

}

