package com.example.internapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internapp.DataClass.topHeadLines
import com.example.internapp.R
import com.example.internapp.RetrofitClient
import com.example.internapp.myAdapter
import com.miguelcatalan.materialsearchview.MaterialSearchView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlaceholderFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        RetrofitClient.retrofitService.getTopNews().enqueue(object : Callback<topHeadLines>{
            override fun onFailure(call: Call<topHeadLines>, t: Throwable) {
                Toast.makeText(context,"Error in getting news : ${t.message}",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<topHeadLines>, response: Response<topHeadLines>) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                if(response.isSuccessful){
                    RetrofitClient.articles!!.clear()
                    RetrofitClient.articles!!.addAll(response.body()!!.articles)
                    RetrofitClient.adapter =  myAdapter(context!!,RetrofitClient.articles!!)
                    recyclerView.adapter = RetrofitClient.adapter
                }
            }
        })
    }
}