package com.khalid.bulletinboard.utils

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.khalid.bulletinboard.R
import io.ak1.pix.helpers.addPixToActivity
import io.ak1.pix.models.Mode
import io.ak1.pix.models.Options
import io.ak1.pix.models.Ratio


const val PICK_IMAGE_CODE = 999

object ImagePicker {

    const val MAX_IMAGE_COUNT = 3

    fun getImage(context: AppCompatActivity, imageCounter: Int) {


        val options = Options().apply {
            ratio = Ratio.RATIO_AUTO                                    //Image/video capture ratio
            count = imageCounter                                        //Number of images to restrict selection count
            spanCount = 4                                               //Number for columns in grid
            path = "Pix/Camera"                                         //Custom Path For media Storage
            isFrontFacing = false                                       //Front Facing camera on start
            mode = Mode.Picture                                         //Option to select only pictures or videos or both
            preSelectedUrls = ArrayList<Uri>()                          //Pre selected Image Urls
        }

        context.addPixToActivity(R.id.edit_frame_container, options)
    }

}