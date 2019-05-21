package com.example.stanh.dyel_app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import kotlinx.android.synthetic.main.activity_history.*
import java.time.LocalDateTime

class HistoryActivity : Activity() {

    private var readBtn: Button?= null
    private var clrBtn: Button?= null
    val context = this

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        readBtn = findViewById(R.id.historyButton)
        var db = DataBaseMax(context)


        readBtn!!.setOnClickListener({
            var data = db.readData()
            //val current = LocalDateTime.now()
            histView.text = ""
            for (i in 0..(data.size-1)){
                histView.append("Your maxes were: " + " \n Bench -> " + data.get(i).bench.toString() + "\n Curl -> " +
                        data.get(i).curl + " \n Shoulder Press -> " + data.get(i).shoulder + "\n \n")
            }

        })

        clrBtn = findViewById(R.id.clearButton)

        clrBtn!!.setOnClickListener({
            db.clearTable()
            histView.setText("The history has been cleared")
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
