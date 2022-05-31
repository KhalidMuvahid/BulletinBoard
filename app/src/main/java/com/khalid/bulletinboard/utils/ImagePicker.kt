package com.khalid.bulletinboard.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

const val PICK_IMAGE_CODE = 999
object ImagePicker {



    fun getImage(context: AppCompatActivity){
        var intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        intent.action = Intent.ACTION_GET_CONTENT
        context.startActivityForResult(Intent.createChooser(intent,"Select image"),PICK_IMAGE_CODE)
    }

}