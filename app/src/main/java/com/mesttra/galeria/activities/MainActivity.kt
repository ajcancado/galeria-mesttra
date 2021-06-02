package com.mesttra.galeria.activities

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mesttra.galeria.R
import com.mesttra.galeria.adapters.PhotosAdapter
import com.mesttra.galeria.api.ApiClient
import com.mesttra.galeria.models.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    var dataList = ArrayList<Photo>()
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.adapter = PhotosAdapter(dataList, this)
        val layoutManager = LinearLayoutManager(this)
//        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.layoutManager = layoutManager

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Carregando...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        getData()
    }

    private fun getData() {

        val call: Call<List<Photo>> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<List<Photo>> {

            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>?) {
                progressDialog.dismiss()
                dataList.addAll(response!!.body()!!)
                recyclerView?.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Photo>>?, t: Throwable?) {
                progressDialog.dismiss()
            }

        })
    }

//    fun isOnline(context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                    return true
//                }
//            }
//        }
//        return false
//    }
}