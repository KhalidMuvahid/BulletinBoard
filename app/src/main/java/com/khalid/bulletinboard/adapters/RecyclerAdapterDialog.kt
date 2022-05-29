package com.khalid.bulletinboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import java.util.ArrayList

class RecyclerAdapterDialog(): RecyclerView.Adapter<RecyclerAdapterDialog.SpViewHolder>() {

    var mainList = ArrayList<String>()

    class SpViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(name:String){
            val tvRecyclerItem = itemView.findViewById<TextView>(R.id.tvCountryName)
            tvRecyclerItem.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return SpViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    fun updateAdapter(list: ArrayList<String>){
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }

}