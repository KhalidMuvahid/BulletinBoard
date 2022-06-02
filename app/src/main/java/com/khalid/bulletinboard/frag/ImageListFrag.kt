package com.khalid.bulletinboard.frag

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.utils.ItemTouchMoveCallback
import io.ak1.pix.helpers.PixBus
import io.ak1.pix.helpers.PixEventCallback
import kotlinx.coroutines.coroutineScope

const val TAG = "Tag"

class ImageListFrag(var frgClose:FragmentClose,var selectedImageData:List<SelectedImageData>):Fragment() {

    var adapter = SelectedImageAdapter()
    val dragCallback = ItemTouchMoveCallback(adapter)
    val touchHelper = ItemTouchHelper(dragCallback)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG,"imageList onCreate View callback")
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var backbutton:Button = view.findViewById(R.id.bBack)

        var recycler = view.findViewById<RecyclerView>(R.id.slectedImRecycler)
        touchHelper.attachToRecyclerView(recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter.updateAdapter(selectedImageData)
        recycler.adapter=adapter

        Log.i(TAG,"imageList View onCreated  ${selectedImageData.size}")

        backbutton.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        frgClose.onClose()
    }
}