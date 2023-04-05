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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.darkmanger.databinding.FragmentFragment2Binding
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
    lateinit var binding:FragmentFragment2Binding
    var totalmoneyEarned :Long =0
    var date:String ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_fragment2, container, false
        )
        val view: View = binding.root
        binding.button.setOnClickListener {
            if(binding.editTextTextPersonName.text.toString() == "1234"){
                binding.textView1.visibility= View.GONE
                binding.textView2.visibility= View.GONE
                binding.textView.visibility= View.VISIBLE
                binding.editTextTextPersonName.visibility= View.GONE
                binding.button.visibility= View.GONE
                binding.button2.visibility= View.VISIBLE
                binding.textView3.visibility= View.VISIBLE
                binding.calenderLay.visibility= View.VISIBLE

            }
            else{
                Toast.makeText(activity,"invalid pass ",Toast.LENGTH_SHORT).show()
            }
        }
        db=FirebaseFirestore.getInstance()
        date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(Instant.now())

        button=view.findViewById(R.id.button2)
        textView=view.findViewById(R.id.textView)

        binding.calendarView
            .setOnDateChangeListener { _, year, month, dayOfMonth ->
//                val Date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
                var newMonth:String ?=null
                var newDay:String?=null
                if(month+1 <10){
                    newMonth=String.format("%02d", month+1);
                    Log.i(TAG, "onCreateView: $newMonth")

                }else{
                        newMonth=(month+1).toString()
                    }
                if(dayOfMonth<10){
                    newDay=String.format("%02d", dayOfMonth);

//                    newDay= "0$dayOfMonth"
                }else{
                    newDay=dayOfMonth.toString()
                }
//                val Date = (year.toString() + "-" + (month + 1) + "-" + dayOfMonth)
                date = ("$year-$newMonth-$newDay")

                // set this date in TextView for Display
               // Toast.makeText(activity, Date, Toast.LENGTH_LONG).show()
            }
        button.setOnClickListener {
            textView.text="Calc .."
            binding.progressBar.visibility=View.VISIBLE

            //Log.i(TAG, "onCreate: ")
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
                    binding.progressBar.visibility=View.GONE

                    totalmoneyEarned=0

                }
                .addOnFailureListener{exception->
                    Log.w(TAG,"Error getting documents.",exception)
                }
        }
        return view
    }



}