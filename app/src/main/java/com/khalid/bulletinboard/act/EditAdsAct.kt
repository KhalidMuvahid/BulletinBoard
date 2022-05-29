package com.khalid.bulletinboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.databinding.ActivityEditAdsBinding
import com.khalid.bulletinboard.databinding.SignDialogBinding
import com.khalid.bulletinboard.dialogs.CountryDialogHelper
import com.khalid.bulletinboard.utils.CountryCityHelper

class EditAdsAct : AppCompatActivity() {

    private lateinit var binding: ActivityEditAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CountryDialogHelper().setCountryDialog(this,CountryCityHelper.getAllCountries(this))



    }
}