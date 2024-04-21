package com.misw.vinilos_g24.brokers

import com.misw.vinilos_g24.models.Album
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitBroker {

    companion object{
        private val albumsList = mutableListOf<String>()
        fun getRequest(onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) {
            var r = RetrofitApi.retrofitService.getProperties()
            var p = r.enqueue(
                object : Callback<List<Album>> {
                    override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                        if (response.isSuccessful) {
                            val albums = response.body()
                            albums?.forEach { album ->
                                albumsList.add(album.name)
                            }
                        }
                    }
                })
        }
        fun postRequest(body: Map<String, String>, onResponse:(resp:String)->Unit, onFailure:(resp:String)->Unit) : String? {
            var resp: String? = null

            RetrofitApi.retrofitService.postProperties(body["name"] ?: "", body["telephone"] ?: "", body["email"] ?:"").enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        onFailure(t.message!!)
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        onResponse(response.body()!!)
                    }
                })
            return resp
        }
    }
}