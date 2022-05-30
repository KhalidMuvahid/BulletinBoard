package com.khalid.bulletinboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

object CountryCityHelper {

    fun getAllCountries(context:Context):ArrayList<String>{
        var tempArray = ArrayList<String>()

        try {
            var inputStream:InputStream = context.assets.open("countriesToCities.json")
            val size:Int = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)

            val jsonFile = String(byteArray)
            val jsonObject = JSONObject(jsonFile)
            val countryNames = jsonObject.names()

            if (countryNames != null){
                for (n in 0 until countryNames.length() ){
                    tempArray.add(countryNames.getString(n))
                }
            }
        }catch (e:IOException){

        }

        return tempArray
    }

    fun getAllCities(context:Context,countryName:String):ArrayList<String>{
        var tempArray = ArrayList<String>()

        try {
            var inputStream:InputStream = context.assets.open("countriesToCities.json")
            val size:Int = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)

            val jsonFile = String(byteArray)
            val jsonObject = JSONObject(jsonFile)
            val cityNames = jsonObject.getJSONArray(countryName)

            if (cityNames != null){
                for (n in 0 until cityNames.length() ){
                    tempArray.add(cityNames.getString(n))
                }
            }
        }catch (e:IOException){

        }

        return tempArray
    }

    fun filterListData(list:ArrayList<String>,search:String?):ArrayList<String>{
        var tempArray = ArrayList<String>()

        if (search != null){
            for (s: String in list) {
                if (s.toLowerCase(Locale.ROOT).startsWith(search.toLowerCase(Locale.ROOT))) {
                    tempArray.add(s)
                }
            }
        }

        if (tempArray.size == 0){
            tempArray.add("No result")
        }
        return tempArray
    }
}