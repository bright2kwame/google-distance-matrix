package com.monarchy.googledistancemetrics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.monarchy.googledistancemetrix.DistanceMetrixData
import com.monarchy.googledistancemetrix.DistanceMetrixInterface
import com.monarchy.googledistancemetrix.MatrixCall
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity(), DistanceMetrixInterface {


    override fun onReceivedDetail(detailMessage: String) {
        textView.text = detailMessage
    }

    override fun onReceivedFailed(failedMessage: String) {
        textView.text = failedMessage
    }

    override fun onReceivedResult(result: DistanceMetrixData) {
        textView.text = result.toString()
    }

    override fun onRequestEnded() {
        Toast.makeText(this, "Request ended", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modes = arrayListOf<String>("driving", "walking", "bicycling", "transit")
        MatrixCall(this, "**************").startDistanceMatrix("5.673127299999999", "-0.1663851", "5.612781", "-0.234345", modes[0], this)


    }
}
