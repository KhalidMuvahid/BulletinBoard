package com.khalid.bulletinboard.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import java.util.ArrayList

class RecyclerAdapterDialog(var textView: TextView, var dialog: AlertDialog): RecyclerView.Adapter<RecyclerAdapterDialog.SpViewHolder>() {

    var mainList = ArrayList<String>()

    class SpViewHolder(itemView: View,var textView: TextView,var dialog: AlertDialog):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var itemName = ""

        fun bind(name:String){
            val tvRecyclerItem = itemView.findViewById<TextView>(R.id.tvCountryName)
            tvRecyclerItem.text = name
            itemView.setOnClickListener(this)
            itemName = name
        }

        override fun onClick(p0: View?) {
            textView.text = itemName
            dialog.dismiss()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        return SpViewHolder(view,textView,dialog)
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