package com.example.myfirstandroidapp

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Tady budeme uchovávat data, která mají přežít rotaci
    // Pro jednoduchost zatím jen proměnná, v praxi LiveData/StateFlow
    var storedUsername: String = ""
    var isUserLoggedIn: Boolean = false
}