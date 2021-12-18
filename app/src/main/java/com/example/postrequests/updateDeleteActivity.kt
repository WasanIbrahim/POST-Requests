package com.example.postrequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.postrequests.databinding.ActivityUpdateDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class updateDeleteActivity : AppCompatActivity() {


    lateinit var binding: ActivityUpdateDeleteBinding

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.deleteButton.setOnClickListener {

            updateTest()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.updateButton.setOnClickListener {

            deleteTest()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }





    }



    private fun updateTest() {

            apiInterface?.updateTest(binding.idText.text.toString().toInt(),TestItem(binding.nameText.text.toString(), binding.locationText.text.toString(),binding.idText.text.toString().toInt()))?.enqueue(object :
                Callback<TestItem> {
                override fun onResponse(call: Call<TestItem>, response: Response<TestItem>) {

                    Log.d("MAIN", "Successfully posted")
                    println("here is the data entered ${binding.nameText.text} and ${binding.locationText.text} and ${binding.idText.text}")


                }
                override fun onFailure(call: Call<TestItem>, t: Throwable) {
                    Log.d("MAIN", "Something went wrong!")
                }

            })
    }


    private fun deleteTest() {
        apiInterface?.updateTest(binding.idText.text.toString().toInt(),TestItem(binding.nameText.text.toString(), binding.locationText.text.toString(),binding.idText.text.toString().toInt()))?.enqueue(object :
            Callback<TestItem> {
            override fun onResponse(call: Call<TestItem>, response: Response<TestItem>) {

                Log.d("MAIN", "Successfully posted")
                println("here is the data entered ${binding.nameText.text} and ${binding.locationText.text} and ${binding.idText.text}")


            }
            override fun onFailure(call: Call<TestItem>, t: Throwable) {
                Log.d("MAIN", "Something went wrong!")
            }

        })
    }


}