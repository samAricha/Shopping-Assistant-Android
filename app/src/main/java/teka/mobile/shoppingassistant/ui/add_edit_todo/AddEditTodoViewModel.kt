package teka.mobile.shoppingassistant.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import teka.mobile.shoppingassistant.data.Todo
import teka.mobile.shoppingassistant.data.TodoRepository
import teka.mobile.shoppingassistant.util.UiEvent
import javax.inject.Inject

//annotation so that hilt knows how to inject its dependency into this view model
@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle //contains our navigation arguments
): ViewModel() {
    //this is where our states are contained.
    var todo by mutableStateOf<Todo?>(null)
        private set//this means the value can only be changed from within our view-model but can be read from outside.

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    //the following lines are for the events we send
    //from view model to UI.
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        //in here we are checking whether we opened the AddEditTodoScreen from
        //an existing to-do or from a new to-do.
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1){
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent){
       when(event){
           is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
           }
           is AddEditTodoEvent.OnDescriptionChange -> {
               description = event.description
           }
           is AddEditTodoEvent.OnSaveTodoClick -> {
               viewModelScope.launch {
                   if(title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The Title can't be empty"
                        ))
                       return@launch
                   }
                   repository.insertTodo(
                       Todo(
                           title = title,
                           description = description,
                           isShopped = todo?.isShopped ?: false,
                           id = todo?.id
                       )
                   )
                   sendUiEvent(UiEvent.PopBackStack)
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