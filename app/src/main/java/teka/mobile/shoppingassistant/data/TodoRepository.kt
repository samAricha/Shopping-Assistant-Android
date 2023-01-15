package teka.mobile.shoppingassistant.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun getTodoById(id: Int):Todo?

    suspend fun deleteTodo(todo: Todo)

    fun getTodos(): Flow<List<Todo>>
}