package com.example.stanh.dyel_app

import android.app.Activity
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.*

class ProfileActivity : Activity() {

    private var histBtn: Button ?= null
    private var rateBtn: Button ?= null
    private var gunNoise: MediaPlayer ?= null
    private var txt1: Int = 0
    private var txt2: Int = 0
    private var txt3: Int = 0

    val context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        histBtn = findViewById(R.id.histButton)
        rateBtn = findViewById(R.id.rateButton)
        gunNoise = MediaPlayer.create(this, R.raw.gun_noise)

        var db = DataBaseMax(context)

         txt1 = benchText.text.toString().toInt()
         txt2 = curlText.text.toString().toInt()
         txt3 = shoulderText.text.toString().toInt()

        histBtn!!.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        rateBtn!!.setOnClickListener({
            gunNoise!!.start()
            rate(txt1,txt2,txt3)
        })



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
        var db = DataBaseMax(context)
        when (item.itemId) {
            R.id.action_save -> {
                //var db = ColorDatabase(context)
                txt1 = benchText.text.toString().toInt()
                txt2 = curlText.text.toString().toInt()
                txt3 = shoulderText.text.toString().toInt()
                db.insertData(txt1, txt2, txt3)
            }
        }

        return when (item.itemId) {
            R.id.action_bar -> {
                val intent = Intent(this, HelpActivity::class.java)
                startActivity(intent)
                true

            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun writeToFile(str: String){
        try{
            var fo = FileWriter("maxValues.txt", true)
            fo.write(str)
            fo.close()
            Toast.makeText(this, "Congrats", Toast.LENGTH_SHORT).show()
        }catch(ex:Exception){
            print(ex.message)
            Toast.makeText(this, "did not save", Toast.LENGTH_SHORT).show()
        }
    }

    fun rate(bench: Int, curl: Int, shoulder: Int){

        var sum = 0
        var avg = 0
        sum = bench + curl + shoulder
        avg = sum/3

        if (avg > 200){
            Toast.makeText(this,"You DEFINITELY Lift!", Toast.LENGTH_SHORT).show()

        }
        else
            if (avg > 130){
                Toast.makeText(this,"Congrats You Lift!", Toast.LENGTH_SHORT).show()
        }
        else
            if (avg > 95){
                Toast.makeText(this,"You're well on your way there!", Toast.LENGTH_SHORT).show()
            }
        else {
            Toast.makeText(this,"You DO NOT Lift!!!", Toast.LENGTH_SHORT).show()
        }

    }






}


