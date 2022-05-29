package com.khalid.bulletinboard.dialogs

import android.app.AlertDialog
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.adapters.RecyclerAdapterDialog
import com.khalid.bulletinboard.utils.CountryCityHelper
import java.util.ArrayList

class CountryDialogHelper {

    fun setCountryDialog(context: Context,list:ArrayList<String>){
        var builder = AlertDialog.Builder(context)
        var rootView = LayoutInflater.from(context).inflate(R.layout.country_dialog_layout,null)
        var adapter = RecyclerAdapterDialog()
        var recycler = rootView.findViewById<RecyclerView>(R.id.spRecyclerView)
        var sv = rootView.findViewById<SearchView>(R.id.svCountry)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter =  adapter
        builder.setView(rootView)
        adapter.updateAdapter(list)
        setSearchViewListner(list,sv,adapter)
        builder.show()
    }

    private fun setSearchViewListner(list: ArrayList<String>, sv: SearchView?, adapter: RecyclerAdapterDialog?) {
        sv?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                p0?.let {
                    adapter?.updateAdapter(CountryCityHelper.filterListData(list, it))
                }
                return true
            }
        })
    }
}