package com.example.foregroundserviceexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button
    private lateinit var serviceStatusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceButton = findViewById(R.id.button_start_service)
        stopServiceButton = findViewById(R.id.button_stop_service)
        serviceStatusTextView = findViewById(R.id.textview_service_status)

        startServiceButton.setOnClickListener {
            startForegroundServiceCompat()
        }

        stopServiceButton.setOnClickListener {
            stopForegroundService()
        }
    }

    private fun startForegroundServiceCompat() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show()
        serviceStatusTextView.text = "Serviço Ativo"
    }

    private fun stopForegroundService() {
        val serviceIntent = Intent(this, MyForegroundService::class.java)
        stopService(serviceIntent)
        Toast.makeText(this, "Serviço Parado", Toast.LENGTH_SHORT).show()
        serviceStatusTextView.text = "Serviço Inativo"
    }
}
