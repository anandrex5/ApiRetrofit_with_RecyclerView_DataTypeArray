package com.company0ne.apiretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import com.company0ne.apiretrofit.API.ApiClient
import com.company0ne.apiretrofit.API.ApiInterface
import com.google.gson.JsonArray
import org.json.JSONArray
import retrofit2.Callback
import retrofit2.Response

/* Api - https://jsonplaceholder.typicode.com/posts/1/comments */

class MainActivity : AppCompatActivity() {

    lateinit var arrayList: ArrayList<HomeViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayList = ArrayList()
        retroFitArray()

//        retroFit()
//        fetchData()
    }

    //this is for Array
    private fun retroFitArray() {
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val call : Call<JsonArray> = apiInterface.getComment()
        call.enqueue(object : Callback<JsonArray>{
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    val array = JSONArray(response.body().toString())
                    Log.d("jsonArrayData", array.toString())

                    //when the data in the form of Array we always use this for loop
                    for (i in 0 until array.length()) {
                        val model = HomeViewModel()
                        val objects = array.getJSONObject(i)

                        //find and set the data
                        model.id = objects.getString("id")
                        model.name = objects.getString("name")
                        model.email = objects.getString("email")
                        model.description = objects.getString("body")
                        arrayList.add(model)
                    }
                    buildRecycler()
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                TODO("Not yet implemented")
            }
        })
    }

    private fun buildRecycler() {
        var recycler : RecyclerView = findViewById(R.id.recyclerView)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = DataAdapter(arrayList)
    }

    private class DataAdapter(var list: ArrayList<HomeViewModel>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {
        class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

                val textViewId: TextView = itemView.findViewById(R.id.post_Id)
                val textViewTitle: TextView = itemView.findViewById(R.id.post_title)
                val textViewEmail: TextView = itemView.findViewById(R.id.post_email)
                val textViewDescription: TextView = itemView.findViewById(R.id.post_description)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
            return DataViewHolder(view)

        }

        override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//            val post = list[position]

            holder.textViewId.text = list[position].id
            holder.textViewTitle.text = list[position].name
            holder.textViewEmail.text = list[position].email
            holder.textViewDescription.text = list[position].description

        }
        override fun getItemCount(): Int {
            return list.size
        }
    }
}
//    //this is for Object code
//    private fun retroFit() {
//        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
//        val call : Call<JsonObject> = apiInterface.getData()
//        call.enqueue(object : Callback<JsonObject>{
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    val objects = JSONObject(response.body().toString())
////                    for (i in 0 until array.length()) {
////                        val objects = array.getJSONObject(i)
////                        Log.d("jsonData", objects.toString())
////                    }
//
//                    Log.d("jsonArrayData", objects.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
////                TODO("Not yet implemented")
//            }
//        })
//    }

////for object code
//private fun fetchData() {
//    val apiInterface = ApiClient.client.create(ApiInterface::class.java)
//    val call: Call<JsonArray> = apiInterface.getPosts()
//    call.enqueue(object : Callback<JsonArray> {
//        override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
//            if (response.isSuccessful) {
//                val posts = Gson().fromJson(response.body(), Array<ApiInterface>::class.java).toList()
//                for (post in posts) {
//                    Log.d("Post"," Successfull")
//                }
//            } else {
//                Log.e("API Error", "Response unsuccessful")
//            }
//        }
//        override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//            Log.e("API Error", t.message.toString())
//        }
//    })
//}
