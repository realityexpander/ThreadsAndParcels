package com.realityexpander.threadsandparcels

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private val TAG = "MainActivity"

//@Volatile  // not needed in kotlin
private var stopThread = false

class MainActivity : AppCompatActivity() {

    private var buttonStartThread: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartThread = findViewById(R.id.button_start_thread)
    }

    fun startThread(view: View?) {
        stopThread = false

        val runnable = ExampleRunnable(10, button = buttonStartThread)
        Thread(runnable).start()

        /*
        ExampleThread thread = new ExampleThread(10);
        thread.start();
        */

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //work
            }
        }).start();
        */
    }

    fun stopThread(view: View?) {
        stopThread = true
    }

    internal class ExampleThread(var seconds: Int) : Thread() {
        override fun run() {
            for (i in 0 until seconds) {
                Log.d(TAG, "startThread: $i")

                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    internal class ExampleRunnable(var seconds: Int, val button: Button?) : Runnable {
        override fun run() {
            for (i in 0 until seconds) {
                if (stopThread) return

                if (i == 5) {
                    /*
                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */

                    /*
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });
                    */

                    // runOnUiThread(Runnable { buttonStartThread?.text = "50%" }) // deprecated / does not compile

                    button?.post { button.text = "50%" }

                    button?.postDelayed({ button.text = "100%" }, 1000)

                }
                Log.d(TAG, "startThread: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}