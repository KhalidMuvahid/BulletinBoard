package com.khalid.bulletinboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.databinding.ActivityEditAdsBinding
import com.khalid.bulletinboard.dialogs.CountryDialogHelper
import com.khalid.bulletinboard.utils.CountryCityHelper

class EditAdsAct : AppCompatActivity() {

    private lateinit var binding: ActivityEditAdsBinding
    lateinit var countryChooser: TextView
    lateinit var countryDialogHelper:CountryDialogHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countryChooser = binding.tvChooseCountry
        countryDialogHelper = CountryDialogHelper()

    }

    fun onSelectCountry(view: View){
        val listOfCountry = CountryCityHelper.getAllCountries(this)
        countryDialogHelper.showCountryDialog(this,listOfCountry,binding.tvChooseCountry)
        if (binding.tvChooseCountry.text.toString() != getString(R.string.choose_city_text) ){
            binding.tvChooseCity.text = getString(R.string.choose_city_text)
        }
    }

    fun onSelectCity(view: View) {
        val selectedCountry = binding.tvChooseCountry.text.toString()
        val listOfCities =CountryCityHelper.getAllCities(this,selectedCountry)
        countryDialogHelper.showCountryDialog(this,listOfCities,binding.tvChooseCity)
    }
}