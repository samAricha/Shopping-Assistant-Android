package teka.mobile.shoppingassistant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isShopped: Boolean,
    @PrimaryKey val id: Int? = null
)
