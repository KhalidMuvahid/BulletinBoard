package com.khalid.bulletinboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.frag.SelectedImageData
import com.khalid.bulletinboard.utils.ItemTouchMoveCallBack

class ListItemAdapter(): RecyclerView.Adapter<ListItemAdapter.ItemHolder>(),ItemTouchMoveCallBack.ItemTouchAdapter {

    var mainArray = ArrayList<SelectedImageData>()

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.list_item_title)
        var image: ImageView = itemView.findViewById(R.id.list_item_image)

        fun bind(selectedImageData: SelectedImageData){
            title.text=selectedImageData.title
            image.setImageURI(selectedImageData.imgUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_image_item,parent,false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(mainArray[position])
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    fun updateAdapter(newList:ArrayList<SelectedImageData>,needClear:Boolean){
        if (needClear) mainArray.clear()
        mainArray.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onMove(startPos: Int, targetPos: Int) {

        val targetItem = mainArray[targetPos]
        mainArray[targetPos] = mainArray[startPos]
        val titleStart = mainArray[targetPos].title
        mainArray[targetPos].title = targetItem.title
        mainArray[startPos] = targetItem
        mainArray[startPos].title = titleStart
        notifyItemMoved(startPos,targetPos)
    }

}