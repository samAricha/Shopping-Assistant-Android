package teka.mobile.shoppingassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//data classes are a concise way of creating classes that just hold data.
@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isShopped: Boolean,
    @PrimaryKey val id: Int? = null
)
