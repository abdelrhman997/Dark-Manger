package com.example.darkmanger

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.darkmanger.model.Device
import com.example.darkmanger.model.GameType
import com.example.darkmanger.viewmodels.UsageViewModel
import com.example.darkmanger.viewmodels.UsageViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)

class Fragment2 : Fragment() {

    lateinit var button: Button
    lateinit var textView: TextView
    private val TAG = "MainActivity"
    lateinit var db: FirebaseFirestore

    var totalmoneyEarned :Long =0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment2, container, false)
        db=FirebaseFirestore.getInstance()
        val date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(Instant.now())

        button=view.findViewById(R.id.button2)
        textView=view.findViewById(R.id.textView)
        button.setOnClickListener {
            textView.text="demo"
            Log.i(TAG, "onCreate: ")
            db.collection(date.toString())
                .get().addOnSuccessListener{result->
                    for(document in result){
//                        Log.d(TAG,"${document.id}=>${document.data.values}")
                      //  Log.d(TAG,"${document.data.values}")
                        val ps:Long = document["price"] as Long
                        Log.d(TAG,"$ps")

                        totalmoneyEarned += ps
                    }
                    textView.text="$totalmoneyEarned EGP"
                    totalmoneyEarned=0

                }
                .addOnFailureListener{exception->
                    Log.w(TAG,"Error getting documents.",exception)
                }
        }
        return view
    }



}