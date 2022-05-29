package com.khalid.bulletinboard.utils

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

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
}