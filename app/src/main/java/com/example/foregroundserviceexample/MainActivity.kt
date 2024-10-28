package com.example.foregroundserviceexample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foregroundserviceexample.ui.theme.ForegroundServiceExampleTheme

class MainActivity : ComponentActivity() {

    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton = findViewById(R.id.button_start_service)
        stopServiceButton = findViewById(R.id.button_stop_service)

        startServiceButton.setOnClickListener {
            startForegroundServiceCompat()
        }

        stopServiceButton.setOnClickListener {
            stopForegroundService()
        }

    }

    private fun stopForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        stopService(serviceIntent)
        Toast.makeText(this, "Serviço parado", Toast.LENGTH_SHORT).show()
    }

    private fun startForegroundServiceCompat() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
        Toast.makeText(this, "Serviço iniciado", Toast.LENGTH_SHORT).show()
    }
}

