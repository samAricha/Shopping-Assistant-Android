package teka.mobile.shoppingassistant.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import teka.mobile.shoppingassistant.data.Todo
import teka.mobile.shoppingassistant.data.TodoRepository
import teka.mobile.shoppingassistant.util.Routes
import teka.mobile.shoppingassistant.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    //In here we will
    //1. Write the Business Logic
    //2. Call repository functions
    //3. and Have our state variables

    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null//used for caching the recently deleted deleted item
    //and when undo is clicked this object is taken and inserted to db.

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnTodoClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO+"?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnDeleteTodoClick ->{
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Todo deleted",
                        action = "undo"
                    ))
                }
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }
            is TodoListEvent.OnDoneChange -> {
                //the following line basically launches a coroutine
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(//in here we are copying the id of the todo for us to update it
                            isShopped = event.isDone
                        )
                    )
                }
            }
        }
    }

    //function for sending an event to a channel
    private fun sendUiEvent(event: UiEvent){
        //the following line binds the lifetime of this coroutine
        //to this view-model lifecycle.
        viewModelScope.launch {
            _uiEvent.send(event)//we are sending a route to a channel

        }
    }

}