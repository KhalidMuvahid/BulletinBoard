package com.khalid.bulletinboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.khalid.bulletinboard.accounthelper.REQUEST_SIGN_IN_CODE
import com.khalid.bulletinboard.act.EditAdsAct
import com.khalid.bulletinboard.databinding.ActivityMainBinding
import com.khalid.bulletinboard.dialoghelper.DialogConst
import com.khalid.bulletinboard.dialoghelper.DialogHelper
import kotlin.math.log

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.new_ads){
            var intent = Intent(this,EditAdsAct::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SIGN_IN_CODE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    dialogHelper.accountHelper.signInFireBaseWithGoogle(account.idToken!!)
                }
            }catch (e:ApiException){
                Log.d("MyLog","exception ${e.message}",)
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
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
                dialogHelper.accountHelper.signOutGoogle()
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