package com.khalid.bulletinboard.frag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.utils.ItemTouchMoveCallback

class SelectedImageAdapter: RecyclerView.Adapter<SelectedImageAdapter.ImageHolder>(),ItemTouchMoveCallback.ItemTouchAdapter{
    var mainArray = ArrayList<SelectedImageData>()

    class ImageHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.sImTitle)
        var selectedImage: ImageView = itemView.findViewById(R.id.selectedImage)

        fun bind(selectedImageData: SelectedImageData){
            title.text = selectedImageData.title
            selectedImage.setImageURI(selectedImageData.imgUri)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.selected_image_item,parent,false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(mainArray[position])
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    fun updateAdapter(newList:List<SelectedImageData>){
        mainArray.clear()
        mainArray.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onMove(startPros:Int, targetPros:Int) {
        val targetItem = mainArray[targetPros]
        mainArray[targetPros] = mainArray[startPros]
        mainArray[targetPros] = targetItem
        notifyItemMoved(startPros,targetPros)
    }
}