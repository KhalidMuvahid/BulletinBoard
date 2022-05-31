package com.khalid.bulletinboard.act

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.databinding.ActivityEditAdsBinding
import com.khalid.bulletinboard.dialogs.CountryDialogHelper
import com.khalid.bulletinboard.utils.CountryCityHelper
import com.khalid.bulletinboard.utils.ImagePicker
import com.khalid.bulletinboard.utils.PICK_IMAGE_CODE


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

    fun onSelectEditViewPager(view: View) {
        ImagePicker.getImage(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_CODE){
            if (resultCode == RESULT_OK){
                if (data != null){
                    if (data.clipData != null){
                        for (i in 0 until data.clipData!!.itemCount){
                            var imgUri = data.clipData!!.getItemAt(i).uri
                            Toast.makeText(this,"suc: $imgUri",Toast.LENGTH_SHORT).show()
                        }
                    }else {
                        var imgUri = data.data
                        Toast.makeText(this,"suc: $imgUri",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}