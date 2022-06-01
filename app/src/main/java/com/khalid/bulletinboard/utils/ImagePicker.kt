package com.khalid.bulletinboard.utils

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.frag.FragmentClose
import com.khalid.bulletinboard.frag.ImageListFrag
import com.khalid.bulletinboard.frag.SelectedImageData
import io.ak1.pix.helpers.PixEventCallback
import io.ak1.pix.helpers.addPixToActivity
import io.ak1.pix.helpers.pixFragment
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import io.ak1.pix.models.Ratio


const val PICK_IMAGE_CODE = 999
object ImagePicker {

    var selectedImageData = ArrayList<SelectedImageData>()

    fun getImage(context: AppCompatActivity,imageCounter:Int,frgClose: FragmentClose){

        val options = Options().apply{
            ratio = Ratio.RATIO_AUTO                                    //Image/video capture ratio
            count = imageCounter                                        //Number of images to restrict selection count
            spanCount = 4                                               //Number for columns in grid
            path = "Pix/Camera"                                         //Custom Path For media Storage
            isFrontFacing = false                                       //Front Facing camera on start
            mode = Mode.Picture                                         //Option to select only pictures or videos or both
            preSelectedUrls = ArrayList<Uri>()                          //Pre selected Image Urls
        }


      context.addPixToActivity(R.id.edit_frame_container, options) {
            when (it.status) {
                PixEventCallback.Status.SUCCESS -> {
                    selectedImageData.clear()
                    for (i in 0 until it.data.size){
                        selectedImageData.add(SelectedImageData("Title $i",it.data[i]))
                    }
                    context.supportFragmentManager.beginTransaction().replace(R.id.edit_frame_container,ImageListFrag(frgClose, selectedImageData)).commit()
                }
                PixEventCallback.Status.BACK_PRESSED -> "// back pressed called"
            }
        }
    }

}