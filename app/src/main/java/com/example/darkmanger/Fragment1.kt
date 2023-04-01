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
@SuppressLint("SetTextI18n")
class Fragment1 : Fragment() {
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var startTimeTV: TextView
    private lateinit var startTimeTV2: TextView
    private lateinit var startTimeTV3: TextView
    private lateinit var startTimeTV4: TextView
    private lateinit var startTimeTV5: TextView

    private lateinit var instant1: Instant
    private lateinit var instant2: Instant
    private lateinit var instant3: Instant
    private lateinit var instant4: Instant
    private lateinit var instant5: Instant
    private val TAG = "MainActivity"
    private var start = false
    private var start2 = false
    private var start3 = false
    private var start4 = false
    private var start5 = false
    lateinit var db: FirebaseFirestore
    private lateinit var button: Button
    var totalMoneyEarned:Int =0

    private val viewModel: UsageViewModel by activityViewModels {
        UsageViewModelFactory(
            (activity?.application as UsageApplication).database
                .deviceDao()
        )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fragment1, container, false)
        initViews(view)
        db=FirebaseFirestore.getInstance()
        val date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(Instant.now())

        btn1.setOnClickListener{
            val device1 = Device("device1",1, Instant.now(), Instant.now(), GameType.SINGLE, 0,start)
            startOrPause(btn1,device1,startTimeTV)
        }
        btn2.setOnClickListener{
            val device2 = Device("device2",2,
                Instant.now(),
                Instant.now(), GameType.SINGLE, 0,start2)
            startOrPause(btn2,device2,startTimeTV2)
        }
        btn3.setOnClickListener{
            val device3 = Device("device3",3,
                Instant.now(),
                Instant.now(), GameType.SINGLE, 0,start3)
            startOrPause(btn3,device3,startTimeTV3)
        }
        btn4.setOnClickListener{
            val device4 = Device("device4",4,
                Instant.now(),
                Instant.now(), GameType.SINGLE, 0,start4)
            startOrPause(btn4,device4,startTimeTV4)
        }
        btn5.setOnClickListener{
            val device5 = Device("device5",5,
                Instant.now(),
                Instant.now(), GameType.SINGLE, 0,start5)
            startOrPause(btn5,device5,startTimeTV5)
        }
        button.setOnClickListener{
            Log.i(TAG, "onCreate: ")
            db.collection(date.toString())
                .get().addOnSuccessListener{result->
                    for(document in result){
//                        Log.d(TAG,"${document.id}=>${document.data.values}")
                        Log.d(TAG,"${document.data.values}")
                        //totalmoneyEarned = totalmoneyEarned + document.data.values
                    }
                }
                .addOnFailureListener{exception->
                    Log.w(TAG,"Error getting documents.",exception)
                }
        }
        return view
    }

    private fun startOrPause(btn:Button, device: Device, textView: TextView) {

        if (!device.isPlaying) {
            btn.text = "stop"
            device.isPlaying = true
            when(btn){
                btn1 ->{
                    instant1= Instant.now()
                    device.startTime = instant1
                    start =true
                    viewModel.addNewDevice(
                        device.name,device.id,device.startTime,device.endTime,device.type,device.price,start
                    )
                }
                btn2 ->{
                    instant2= Instant.now()
                    device.startTime = instant2
                    start2 =true
                    viewModel.addNewDevice(
                        device.name,device.id,device.startTime,device.endTime,device.type,device.price,start2
                    )
                }
                btn3 ->{
                    instant3= Instant.now()
                    device.startTime = instant3
                    start3 =true
                    viewModel.addNewDevice(
                        device.name,device.id,device.startTime,device.endTime,device.type,device.price,start3
                    )
                }
                btn4 ->{
                    instant4= Instant.now()
                    device.startTime = instant4
                    start4 =true
                    viewModel.addNewDevice(
                        device.name,device.id,device.startTime,device.endTime,device.type,device.price,start4
                    )
                }
                btn5 ->{
                    instant5= Instant.now()
                    device.startTime = instant5
                    start5 =true
                    viewModel.addNewDevice(
                        device.name,device.id,device.startTime,device.endTime,device.type,device.price,start5
                    )
                }

            }
            when(textView){
                startTimeTV -> {
                    startTimeTV.visibility=View.VISIBLE
                    startTimeTV.text = DateTimeFormatter
                        .ofPattern(" HH:mm:ss a")
                        .withZone(ZoneId.of("UTC+2"))
                        .format(device.startTime)}
                startTimeTV2 -> {
                    startTimeTV2.visibility=View.VISIBLE
                    startTimeTV2.text =  DateTimeFormatter
                        .ofPattern(" HH:mm:ss a")
                        .withZone(ZoneId.of("UTC+2"))
                        .format(device.startTime)
                }
                startTimeTV3 ->{
                    startTimeTV3.visibility=View.VISIBLE
                    startTimeTV3.text =  DateTimeFormatter
                        .ofPattern(" HH:mm:ss a")
                        .withZone(ZoneId.of("UTC+2"))
                        .format(device.startTime)}
                startTimeTV4 ->{
                    startTimeTV4.visibility=View.VISIBLE
                    startTimeTV4.text =  DateTimeFormatter
                        .ofPattern(" HH:mm:ss a")
                        .withZone(ZoneId.of("UTC+2"))
                        .format(device.startTime)}
                startTimeTV5 ->{
                    startTimeTV5.visibility=View.VISIBLE
                    startTimeTV5.text =  DateTimeFormatter
                        .ofPattern(" HH:mm:ss a")
                        .withZone(ZoneId.of("UTC+2"))
                        .format(device.startTime)}
            }

        }
        else {
            btn.text = "start"
            device.isPlaying = false
            when(btn){
                btn1 ->{
                    calcCost(instant1,device,device.dId)
                    start=false
                    startTimeTV.visibility=View.GONE
                }
                btn2 ->{
                    startTimeTV2.visibility=View.GONE

                    calcCost(instant2,device,device.dId)
                    start2=false}
                btn3 ->{
                    startTimeTV3.visibility=View.GONE

                    calcCost(instant3,device,device.dId)
                    start3=false}
                btn4 ->{
                    startTimeTV4.visibility=View.GONE

                    calcCost(instant4,device,device.dId)
                    start4=false}
                btn5 ->{
                    startTimeTV5.visibility=View.GONE

                    calcCost(instant5,device,device.dId)
                    start5=false}

            }


        }
    }

    private fun initViews(view: View) {
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btn5 = view.findViewById(R.id.btn5)
        startTimeTV=view.findViewById(R.id.startTimeTV)
        startTimeTV2=view.findViewById(R.id.startTimeTV2)
        startTimeTV3=view.findViewById(R.id.startTimeTV3)
        startTimeTV4=view.findViewById(R.id.startTimeTV4)
        startTimeTV5=view.findViewById(R.id.startTimeTV5)
        button=view.findViewById(R.id.button)

    }


    private fun showDialog(title: Device,minutes:Long,activity: Activity,did:Int) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_layout)

        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as Button

        yesBtn.setOnClickListener {
            dialog.dismiss()
            sendToFireStore(GameType.MULTY,title,minutes,requireActivity(),did)

        }
        noBtn.setOnClickListener {
            dialog.dismiss()
            sendToFireStore(GameType.SINGLE,title,minutes,requireActivity(),did)

        }
        dialog.show()


    }
    private fun calcCost(instant: Instant, device: Device, dId: Int){
        val instant2 = Instant.now()
        val diff: Duration = Duration.between(instant, instant2)
        val minutes = diff.toMinutes()
        device.endTime = instant2
        showDialog(device, minutes, requireActivity(),dId)
    }
    private fun sendToFireStore(type:GameType,title: Device,minutes:Long,activity: Activity,did:Int){

        val price:Double
        if(type==GameType.MULTY){
            price = if(title.name == "device3"){
                minutes.toDouble() *(40.0/60.0)

            }else{
                minutes.toDouble() *(30.0/60.0)

            }
            title.price = price.toInt()

            Toast.makeText(activity,"price is $price  ", Toast.LENGTH_LONG).show()
            viewModel.updateDevice(
                title.name,title.id,title.startTime,title.endTime,title.type,price.toInt(),false,did
            )
            val map = HashMap<String,Any>()
            map[title.name] = title.name
            map["price"] = price.toInt()
            map["startTime"]= DateTimeFormatter
                .ofPattern(" HH:mm:ss a")
                .withZone(ZoneId.of("UTC+2"))
                .format(title.startTime)
            map["endTime"]= DateTimeFormatter
                .ofPattern(" HH:mm:ss a")
                .withZone(ZoneId.of("UTC+2"))
                .format(title.endTime)
            val date= DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(title.startTime)
            val dayRef=db.collection(date).document()
            dayRef.set(map)

        }
        else{
            price = if(title.name == "device3"){
                minutes.toDouble() *(30.0/60.0)

            }else{
                minutes.toDouble() *(20.0/60.0)

            }
            title.price = price.toInt()
            Toast.makeText(activity,"price is ${price.toInt()}  ", Toast.LENGTH_LONG).show()
            val map = HashMap<String,Any>()
            map[title.name] = title.name
            map["price"] = price.toInt()
            map["startTime"]= DateTimeFormatter
                .ofPattern(" HH:mm:ss a")
                .withZone(ZoneId.of("UTC+2"))
                .format(title.startTime)
            map["endTime"]= DateTimeFormatter
                .ofPattern(" HH:mm:ss a")
                .withZone(ZoneId.of("UTC+2"))
                .format(title.endTime)
            val date= DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(title.startTime)
            val dayRef=db.collection(date).document()
            dayRef.set(map)
            viewModel.updateDevice(
                title.name,title.id,title.startTime,title.endTime,title.type,price.toInt(),false,did
            )
            //db.collection("Money").add(map)
        }
    }

}