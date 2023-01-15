package teka.mobile.shoppingassistant.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import teka.mobile.shoppingassistant.data.TodoDatabase
import teka.mobile.shoppingassistant.data.TodoRepository
import teka.mobile.shoppingassistant.data.TodoRepositoryImpl
import javax.inject.Singleton

@Module//tells dagger we have a module here
@InstallIn(SingletonComponent::class)//defines how long
// our dependencies are going to live.In our place
// its for the whole lifetime of the app because it's singleton
object AppModule {
    //Now onto how dagger can create our room database

    @Provides//this function will provide a dependency
    @Singleton//the dependency will be a singleton hence only a single instance of it will exist
    fun provideTodoDatabase(app: Application):TodoDatabase{
        //whatever is being returned is the database instance
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }


}