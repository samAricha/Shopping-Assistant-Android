package teka.mobile.shoppingassistant.ui.todo_list

import teka.mobile.shoppingassistant.data.Todo

//this is for any event that happens on the screen i.e
//TodoList screeen N/B: Do this for every single screen
sealed class TodoListEvent{
    //what kind of user interactions can we do
    //in the todoList screen.

    data class onTodoClick(val todo: Todo): TodoListEvent()
    data class onDeleteTodoClick(val todo: Todo): TodoListEvent()
    data class onDoneChange(val todo: Todo, val isShopped: Boolean): TodoListEvent()
    object onAddTodoClick: TodoListEvent()
    object onUndoDeleteClick: TodoListEvent()
}

