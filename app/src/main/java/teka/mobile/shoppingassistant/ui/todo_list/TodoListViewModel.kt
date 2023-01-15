package teka.mobile.shoppingassistant.ui.todo_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import teka.mobile.shoppingassistant.data.TodoRepository
import teka.mobile.shoppingassistant.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    //In here we will
    //1. Write the Business Login
    //2. Call repository functions
    //3. and Have our state variables

    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()

}