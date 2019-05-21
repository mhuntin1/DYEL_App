package com.example.stanh.dyel_app

import android.app.Activity
import android.content.Context
import android.content.Intent

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.stanh.dyel_app.R.attr.background
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        //lightView.text = ("Light Level: " + event!!.values[0]).toString()

        if (event!!.values[0] > 1.0){
            lightView.text = ("Great time to work out!")
        }
        else {
            lightView.text = ("Great time to Stop!")
        }
    }

    private var profBtn: Button?= null
    private  var compBtn: Button ?= null

    lateinit var sensorManager: SensorManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL
        )

        profBtn = this.findViewById(R.id.profileButton)

        profBtn!!.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        compBtn = findViewById(R.id.compareButton)

        compBtn!!.setOnClickListener{
            //val intent = Intent(this, InfoActivity::class.java)
            var intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Let's hit up the gym!")
            intent.type = "text/plain"
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_bar -> {
                val intent = Intent(this, HelpActivity::class.java)
                startActivity(intent)
                true

            }

            else -> super.onOptionsItemSelected(item)
        }
    }



}
