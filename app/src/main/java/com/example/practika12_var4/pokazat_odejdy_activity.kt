package com.example.practika12_var4

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class pokazat_odejdy_activity : AppCompatActivity() {

    private var odejdaList: MutableList<odejda> = mutableListOf()
    private lateinit var rv: RecyclerView
    var indexChanged = -1
    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokazat_odejdy_activity)
        getodeja()

        adapter = RVAdapter(this, odejdaList)
        val rvListener = object : RVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@pokazat_odejdy_activity, dobavit_odejdy_activity::class.java)
                intent.putExtra("num", position)
                indexChanged = position
                startActivity(intent)
                Toast.makeText(this@pokazat_odejdy_activity, "Вы выбрали $position вкладку", Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setClickListener(rvListener)
        rv = findViewById(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    private fun getodeja() {
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        }else{
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        odejdaList = Gson().fromJson<MutableList<odejda>>(json, object : TypeToken<MutableList<odejda>>(){}.type)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        if (indexChanged != -1){
            odejdaList.clear()
            getodeja()
            rv.adapter?.notifyDataSetChanged()
        }
    }
}