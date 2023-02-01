package teka.mobile.shoppingassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import teka.mobile.shoppingassistant.ui.add_edit_todo.AddEditTodoScreen
import teka.mobile.shoppingassistant.ui.theme.ShoppingAssistantTheme
import teka.mobile.shoppingassistant.ui.todo_list.TodoListScreen
import teka.mobile.shoppingassistant.util.Routes

@AndroidEntryPoint//annotation from dagger-hilt and is necessary as soon as we want to inject dependencies into an androd component
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingAssistantTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST
                ){
                    composable(Routes.TODO_LIST){
                        TodoListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                    composable(
                        route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditTodoScreen(onPopupBackStack = {
                            navController.popBackStack()
                        })
                    }
                }


            }
        }
    }
}
