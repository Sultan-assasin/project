package com.sultan.App

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sultan.App.data.ApiInterface
import com.sultan.App.data.UserItem
import com.sultan.App.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
private lateinit var binding: ActivityMainBinding
lateinit var linearManger: LinearLayoutManager
lateinit  var userAdapter : UserAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setHasFixedSize(true)
        linearManger = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearManger
        binding.progressBarLarge.visibility = View.VISIBLE





        getData()
        drawLayout()
    }

      fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<UserItem>?> {
            override fun onResponse(
                call: Call<List<UserItem>?>,
                response: Response<List<UserItem>?>
            ) {
                val responseBody = response.body()!!

                userAdapter = UserAdapter(baseContext , responseBody)
                userAdapter.notifyDataSetChanged()
                binding.recyclerView.adapter = userAdapter


            }

            override fun onFailure(call: Call<List<UserItem>?>, t: Throwable) {

            }

        })
    }
    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            Log.d("Internet" , "internet is have connection")
        } else {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}