package com.khalid.bulletinboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.databinding.ActivityEditAdsBinding
import com.khalid.bulletinboard.databinding.SignDialogBinding
import com.khalid.bulletinboard.utils.CountryCityHelper

class EditAdsAct : AppCompatActivity() {

    private lateinit var binding: ActivityEditAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,CountryCityHelper.getAllCountries(this))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCountry.adapter = adapter


    }
}