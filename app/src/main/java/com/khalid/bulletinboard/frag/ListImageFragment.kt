package com.khalid.bulletinboard.frag

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.act.EditAdsAct
import com.khalid.bulletinboard.adapters.ListItemAdapter
import com.khalid.bulletinboard.databinding.FragmentListImageBinding
import com.khalid.bulletinboard.utils.ImagePicker
import com.khalid.bulletinboard.utils.ItemTouchMoveCallBack
import io.ak1.pix.PixFragment
import io.ak1.pix.helpers.PixBus
import io.ak1.pix.helpers.PixEventCallback
import io.ak1.pix.helpers.pixFragment
import io.ak1.pix.models.Options


const val TAG = "TAG"

class ListImageFragment(var frgClose: FragmentClose,var data: List<Uri>) : Fragment() {

    var listItemAdapter = ListItemAdapter()
    lateinit var binding:FragmentListImageBinding
    var touchHelper = ItemTouchHelper(ItemTouchMoveCallBack(listItemAdapter))


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListImageBinding.inflate(layoutInflater)
        setUpToolbar()
        touchHelper.attachToRecyclerView(binding.listImageRecycler)
        binding.listImageRecycler.adapter = listItemAdapter
        binding.listImageRecycler.layoutManager = LinearLayoutManager(activity)

        var updateData = ArrayList<SelectedImageData>()
        for (i in data.indices){
            updateData.add(SelectedImageData(i.toString(),data[i]))
        }
        listItemAdapter.updateAdapter(updateData,true)


        return binding.root
    }

    fun setUpToolbar(){

        binding.listItemTb.inflateMenu(R.menu.menu_choose_image)

        var deleteImgBt = binding.listItemTb.menu.findItem(R.id.delete_image)

        deleteImgBt.setOnMenuItemClickListener {
            listItemAdapter.updateAdapter(ArrayList(),true)
            true
        }

        var addImgBt = binding.listItemTb.menu.findItem(R.id.add_image)

        addImgBt.setOnMenuItemClickListener {
            val imageCount = ImagePicker.MAX_IMAGE_COUNT - listItemAdapter.mainArray.size
            ImagePicker.getImage(activity as AppCompatActivity,imageCount)
            PixBus.results {
                when(it.status){
                    PixEventCallback.Status.SUCCESS -> {
                        updateAdapter(it.data)

                    }
                }
            }
            true
        }

        binding.listItemTb.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            frgClose.onFrgClose(listItemAdapter.mainArray)
        }

    }

    fun updateAdapter(newList : List<Uri>){
        var updateData = ArrayList<SelectedImageData>()
        for (i in listItemAdapter.mainArray.size until newList.size - listItemAdapter.mainArray.size){
            updateData.add(SelectedImageData(i.toString(),data[i-listItemAdapter.mainArray.size]))
        }
        listItemAdapter.updateAdapter(updateData,false)
    }



}