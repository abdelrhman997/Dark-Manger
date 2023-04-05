package com.example.darkmanger

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.darkmanger.databinding.FragmentFragment1Binding
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
    private lateinit var btn1: ImageButton
    private lateinit var btn2: ImageButton
    private lateinit var btn3: ImageButton
    private lateinit var btn4: ImageButton
    private lateinit var btn5: ImageButton
    private lateinit var startTimeTV: TextView
    private lateinit var startTimeTV2: TextView
    private lateinit var startTimeTV3: TextView
    private lateinit var startTimeTV4: TextView
    private lateinit var startTimeTV5: TextView

    private val TAG = "MainActivity"

    lateinit var db: FirebaseFirestore
    lateinit var binding:FragmentFragment1Binding
    private val viewModel: UsageViewModel by activityViewModels {
        UsageViewModelFactory(
            (activity?.application as UsageApplication).database
                .deviceDao()
        )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_fragment1, container, false
        )
        val view: View = binding.root
        binding.imageStatus = viewModel.start1.value!!
        binding.imageStatus2= viewModel.start2.value!!
        binding.imageStatus3= viewModel.start3.value!!
        binding.imageStatus4= viewModel.start4.value!!
        binding.imageStatus5= viewModel.start5.value!!
        initViews(view)
        db=FirebaseFirestore.getInstance()
        binding.lifecycleOwner = this

        // val date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(Instant.now())

        btn1.setOnClickListener{
            val device1 = Device("device1",1, Instant.now(), Instant.now(), GameType.SINGLE, 0,viewModel.start1.value!!)
            startOrPause(btn1,device1,startTimeTV)
        }
        btn2.setOnClickListener{
            val device2 = Device("device2",2, Instant.now(), Instant.now(), GameType.SINGLE, 0,viewModel.start2.value!!)
            startOrPause(btn2,device2,startTimeTV2)
        }
        btn3.setOnClickListener{
            val device3 = Device("device3",3, Instant.now(), Instant.now(), GameType.SINGLE, 0,viewModel.start3.value!!)
            startOrPause(btn3,device3,startTimeTV3)
        }
        btn4.setOnClickListener{
            val device4 = Device("device4",4, Instant.now(), Instant.now(), GameType.SINGLE, 0,viewModel.start4.value!!)
            startOrPause(btn4,device4,startTimeTV4)
        }
        btn5.setOnClickListener{
            val device5 = Device("device5",5, Instant.now(), Instant.now(), GameType.SINGLE, 0,viewModel.start5.value!!)
            startOrPause(btn5,device5,startTimeTV5)
        }


        return view
    }

    override fun onResume() {
        super.onResume()
        changeUI()

    }

    private fun changeUI()
    {
        binding.imageStatus=viewModel.start1.value!!
        binding.imageStatus2=viewModel.start2.value!!
        binding.imageStatus3=viewModel.start3.value!!
        binding.imageStatus4=viewModel.start4.value!!
        binding.imageStatus5=viewModel.start5.value!!
        startTimeTV.text =viewModel.time1.value
        startTimeTV2.text =viewModel.time2.value
        startTimeTV3.text =viewModel.time3.value
        startTimeTV4.text =viewModel.time4.value
        startTimeTV5.text =viewModel.time5.value
    }
    private fun startOrPause(btn:ImageButton, device: Device, textView: TextView) {

        if (!device.isPlaying) {
            btn.setImageResource(R.drawable.baseline_stop_24)

            device.isPlaying = true
            when(btn){
                btn1 ->{
                    viewModel.instant.value= Instant.now()
                    device.startTime = viewModel.instant.value!!
                    viewModel.start1.value =true
                    viewModel.addNewDevice(device.name,device.id,device.startTime,device.endTime,device.type,device.price,viewModel.start1.value!!)
                    changeUI()
                }
                btn2 ->{
                    viewModel.instant2.value= Instant.now()
                    device.startTime =  viewModel.instant2.value!!
                    viewModel.start2.value =true
                    viewModel.addNewDevice(device.name,device.id,device.startTime,device.endTime,device.type,device.price,viewModel.start2.value!!)
                    changeUI()
                }
                btn3 ->{
                    viewModel.instant3.value= Instant.now()
                    device.startTime = viewModel.instant3.value!!
                    viewModel.start3.value =true
                    viewModel.addNewDevice(device.name,device.id,device.startTime,device.endTime,device.type,device.price,viewModel.start3.value!!)
                    changeUI()
                }
                btn4 ->{
                    viewModel.instant4.value= Instant.now()
                    device.startTime = viewModel.instant4.value!!
                    viewModel.start4.value =true
                    viewModel.addNewDevice(device.name,device.id,device.startTime,device.endTime,device.type,device.price,viewModel.start4.value!!)
                    changeUI()
                }
                btn5 ->{
                    viewModel.instant5.value= Instant.now()
                    device.startTime = viewModel.instant5.value!!
                    viewModel.start5.value =true
                    viewModel.addNewDevice(device.name,device.id,device.startTime,device.endTime,device.type,device.price,viewModel.start5.value!!)
                    changeUI()
                }

            }
            when(textView){
                startTimeTV -> {
                    viewModel.time1.value=DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(device.startTime)
                    startTimeTV.text =viewModel.time1.value }
                startTimeTV2 -> {
                    viewModel.time2.value=DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(device.startTime)
                    startTimeTV2.text =  viewModel.time2.value
                }
                startTimeTV3 ->{
                    viewModel.time3.value=DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(device.startTime)
                    startTimeTV3.text = viewModel.time3.value}
                startTimeTV4 ->{
                    viewModel.time4.value=DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(device.startTime)
                    startTimeTV4.text =  viewModel.time4.value}
                startTimeTV5 ->{
                    viewModel.time5.value=DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(device.startTime)
                    startTimeTV5.text = viewModel.time5.value }
            }

        }
        else {
            btn.setImageResource(R.drawable.baseline_play_arrow_24)
            device.isPlaying = false
            when(btn){
                btn1 ->{
                    calcCost(viewModel.instant.value!!,device,device.dId)
                    viewModel.start1.value=false
                    changeUI()
                }
                btn2 ->{
                    calcCost(viewModel.instant2.value!!,device,device.dId)
                    viewModel.start2.value=false
                    changeUI()
                }
                btn3 ->{
                    calcCost(viewModel.instant3.value!!,device,device.dId)
                    viewModel.start3.value=false
                    changeUI()

                }
                btn4 ->{
                    calcCost(viewModel.instant4.value!!,device,device.dId)
                    viewModel.start4.value=false
                    changeUI()

                }
                btn5 ->{
                    calcCost(viewModel.instant5.value!!,device,device.dId)
                    viewModel.start5.value=false
                    changeUI()

                }

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

            title.type=type
            Toast.makeText(activity,"price is $price  ", Toast.LENGTH_LONG).show()
            viewModel.updateDevice(
                title.name,title.id,title.startTime,title.endTime,title.type,price.toInt(),false,did
            )
            val map = HashMap<String,Any>()
            map[title.name] = title.name
            map["price"] = price.toInt()
            map["startTime"]= DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(title.startTime)
            map["endTime"]= DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(title.endTime)
            val date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(title.startTime)
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
            map["startTime"]= DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(title.startTime)
            map["endTime"]= DateTimeFormatter.ofPattern(" HH:mm:ss a").withZone(ZoneId.of("UTC+2")).format(title.endTime)
            val date= DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC).format(title.startTime)
            val dayRef=db.collection(date).document()
            dayRef.set(map)
            viewModel.updateDevice(
                title.name,title.id,title.startTime,title.endTime,title.type,price.toInt(),false,did
            )
        }
    }

}