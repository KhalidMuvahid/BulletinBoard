package com.khalid.bulletinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.khalid.bulletinboard.databinding.ActivityMainBinding
import com.khalid.bulletinboard.dialoghelper.DialogConst
import com.khalid.bulletinboard.dialoghelper.DialogHelper

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private val binding:ActivityMainBinding
        get() = _binding!!
    private var _binding:ActivityMainBinding?=null
    val mAuth = FirebaseAuth.getInstance()
    private val dialogHelper = DialogHelper(this)
    private lateinit var tvEmailNavHeader:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainContent.toolbar)

        val toggle:ActionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.mainContent.toolbar,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        tvEmailNavHeader = binding.navView.getHeaderView(0).findViewById(R.id.tvEmailNavHeader)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ad_ads -> Toast.makeText(this,"Мои обявления",Toast.LENGTH_SHORT).show()
            R.id.ad_car -> Toast.makeText(this,"Cars",Toast.LENGTH_SHORT).show()
            R.id.ad_pc -> Toast.makeText(this,"pc",Toast.LENGTH_SHORT).show()
            R.id.ad_smart -> Toast.makeText(this,"smart",Toast.LENGTH_SHORT).show()
            R.id.ad_dm -> Toast.makeText(this,"dm",Toast.LENGTH_SHORT).show()
            R.id.ac_sign_up -> {
                dialogHelper.createSignDialog(DialogConst.SING_UP_STATE)
            }
            R.id.ac_sign_in -> {
                dialogHelper.createSignDialog(DialogConst.SING_IN_STATE)
            }
            R.id.ac_sign_out -> {
                Toast.makeText(this,"out",Toast.LENGTH_SHORT).show()
                navHeaderUpdate(null)
                mAuth.signOut()
            }
            else -> Toast.makeText(this,"Null",Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun navHeaderUpdate(user:FirebaseUser?){
        if (user == null){
            tvEmailNavHeader.text = resources.getString(R.string.not_reg)
        }else{
            tvEmailNavHeader.text = user.email
        }

    }
}