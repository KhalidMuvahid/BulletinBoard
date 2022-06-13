package com.khalid.bulletinboard.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.adapters.EditViewPagerAdapter
import com.khalid.bulletinboard.databinding.ActivityEditAdsBinding
import com.khalid.bulletinboard.dialogs.CountryDialogHelper
import com.khalid.bulletinboard.frag.FragmentClose
import com.khalid.bulletinboard.frag.ListImageFragment
import com.khalid.bulletinboard.frag.SelectedImageData
import com.khalid.bulletinboard.utils.CountryCityHelper
import com.khalid.bulletinboard.utils.ImagePicker
import io.ak1.pix.helpers.PixBus
import io.ak1.pix.helpers.PixEventCallback

class EditAdsAct : AppCompatActivity(), FragmentClose {


    var chooseImageFrag:ListImageFragment? = null
    private lateinit var binding: ActivityEditAdsBinding
    lateinit var countryChooser: TextView
    lateinit var editViewPagerAdapter: EditViewPagerAdapter
    lateinit var countryDialogHelper:CountryDialogHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countryChooser = binding.tvChooseCountry
        countryDialogHelper = CountryDialogHelper()
        editViewPagerAdapter = EditViewPagerAdapter()
        binding.vpImg.adapter = editViewPagerAdapter

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
        ImagePicker.getImage(this,3)
        PixBus.results {
            when(it.status){
                PixEventCallback.Status.SUCCESS -> {
                    binding.svEditAds.visibility=View.GONE
                    if(chooseImageFrag == null){
                        chooseImageFrag = ListImageFragment(this,it.data)
                        supportFragmentManager.beginTransaction().replace(R.id.edit_frame_container,chooseImageFrag!!).commit()
                    }
                }
            }
        }

    }

    override fun onFrgClose(newList: List<SelectedImageData>) {
        binding.svEditAds.visibility= View.VISIBLE
        editViewPagerAdapter.updateAdapter(newList)
        chooseImageFrag = null
    }
}