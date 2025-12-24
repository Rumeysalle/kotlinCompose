package com.example.view

import BottomNavigationBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.view.ui.theme.ViewTheme
import androidx.compose.ui.Modifier
import com.example.view.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ViewActivity: ComponentActivity()
{
    @Inject
    lateinit var analytics: AnalyticsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
             ViewTheme {
                 Surface(
                     modifier = Modifier.fillMaxSize(),
                     color = MaterialTheme.colorScheme.background

                 ) {
                     MainApp()
                 }
             } 
        }
    }
}
@Composable
fun MainApp(){
    val navController= rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController=navController)
        },
        containerColor = Color(0xFF0F0E0E),
        modifier = Modifier.fillMaxSize()
    ) {
        paddingValues->
        NavGraph(navController=navController,
            modifier = Modifier.padding(paddingValues))

    }
}

