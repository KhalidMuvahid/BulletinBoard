package com.khalid.bulletinboard.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.adapters.RecyclerAdapterDialog
import com.khalid.bulletinboard.utils.CountryCityHelper
import java.util.ArrayList

class CountryDialogHelper {

    fun showCountryDialog(context: Context, list:ArrayList<String>,textView: TextView){
        var builder = AlertDialog.Builder(context)
        var dialog = builder.create()

        var rootView = LayoutInflater.from(context).inflate(R.layout.country_dialog_layout,null)
        var adapter = RecyclerAdapterDialog(textView,dialog)

        var recycler = rootView.findViewById<RecyclerView>(R.id.spRecyclerView)
        var sv = rootView.findViewById<SearchView>(R.id.svCountry)

        adapter.updateAdapter(list)
        recycler.adapter =  adapter
        recycler.layoutManager = LinearLayoutManager(context)

        dialog.setView(rootView)
        dialog.show()
        setSearchViewListener(list,sv,adapter)


    }

    private fun setSearchViewListener(list: ArrayList<String>, sv: SearchView?, adapter: RecyclerAdapterDialog) {
        sv?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var tempList = CountryCityHelper.filterListData(list,p0)
                adapter.updateAdapter(tempList)
                return true
            }

        })
    }
}