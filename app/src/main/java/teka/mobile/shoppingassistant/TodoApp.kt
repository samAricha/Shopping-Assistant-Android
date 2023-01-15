package teka.mobile.shoppingassistant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//this class is used by dagger hilt to gain access to the
//application and its contexts
@HiltAndroidApp
class TodoApp: Application()