package teka.mobile.shoppingassistant.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//this line will update the data when it finds its present
    suspend fun insertTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE id= :id")
    suspend fun getTodoById(id: Int):Todo?

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>
}