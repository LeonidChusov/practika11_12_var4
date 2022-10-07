package com.example.practika12_var4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(context: Context?, val data: MutableList<odejda>): RecyclerView.Adapter<RVAdapter.WeatherViewHolder?>() {

    private val layoutInflater: LayoutInflater = android.view.LayoutInflater.from(context)
    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = data[position]
        holder.temperatureTextView.text = item.temperature
        holder.cityTextView.text = item.city
        holder.numberTextView.text = item.date
        holder.monthTextView.text = item.month
        holder.yearTextView.text = item.year
    }

    override fun getItemCount(): Int = data.size

    inner class WeatherViewHolder(item: View): RecyclerView.ViewHolder(item), View.OnClickListener {
        var temperatureTextView: TextView = item.findViewById(R.id.tv_temperature)
        var cityTextView: TextView = item.findViewById(R.id.tv_city)
        var numberTextView: TextView = item.findViewById(R.id.tv_number)
        var monthTextView: TextView = item.findViewById(R.id.tv_month)
        var yearTextView: TextView = item.findViewById(R.id.tv_year)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            iClickListener?.onItemClick(view, adapterPosition)
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }
}