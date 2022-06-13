package com.khalid.bulletinboard.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.frag.SelectedImageData

class EditViewPagerAdapter:RecyclerView.Adapter<EditViewPagerAdapter.ImageHolder>() {

    private var mainArray = ArrayList<SelectedImageData>()

    class ImageHolder(item: View):RecyclerView.ViewHolder(item){
        var itemImg:ImageView = itemView.findViewById(R.id.edit_image)

        fun bind(imageUri: Uri){
            itemImg.setImageURI(imageUri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.edit_view_pager_item,parent,false)
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(mainArray[position].imgUri)
    }

    override fun getItemCount(): Int {
        return mainArray.size
    }

    fun updateAdapter(newList:List<SelectedImageData>){
        mainArray.clear()
        mainArray.addAll(newList)
        notifyDataSetChanged()
    }
}