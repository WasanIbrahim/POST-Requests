package com.example.postrequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequests.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var myRV: RecyclerView

    val APIList = ArrayList<TestItem>()

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myRV = findViewById(R.id.mainRV)
        myRV.layoutManager = LinearLayoutManager(this)

        getAPI()

        binding.postButton.setOnClickListener {
            postAPI()
            binding.nameText.text = null
            binding.locationText.text = null
        }
        binding.updateDeleteButton.setOnClickListener {
            val intent = Intent(this, updateDeleteActivity::class.java)
            startActivity(intent)
        }

    }


    //getting and presenting the data
    fun getAPI() {

        Log.d("respoNoE", "${apiInterface}")
        val dataRecieved = apiInterface?.getTest()
        println("here is the data received: $dataRecieved ")

        dataRecieved?.enqueue(object : Callback<ArrayList<TestItem>> {
            override fun onResponse(
                call: Call<ArrayList<TestItem>>,
                response: Response<ArrayList<TestItem>>
            ) {
                val myResponse = response.body()
                if (myResponse != null) {
                    for (i in myResponse) {
                        APIList.add(i)
                        //println("here is the APIList $APIList")
                        myRV.adapter = RecyclerViewAdapter(APIList)
                        myRV.adapter?.notifyDataSetChanged()
                        println("here is the APIList $APIList")

                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<TestItem>>, t: Throwable) {
                Log.d("response", "failed to get data , ${t.message}")
            }
        })

    }


    //postong
    fun postAPI() {
        apiInterface?.postTest(TestItem(binding.nameText.text.toString(), binding.locationText.text.toString(),404))?.enqueue(object : Callback<TestItem> {
            override fun onResponse(call: Call<TestItem>, response: Response<TestItem>) {

                Log.d("MAIN", "Successfully posted")

                println("here is the data entered ${binding.nameText.text} and ${binding.locationText.text}")
            }
            override fun onFailure(call: Call<TestItem>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }

        })

    }



}




