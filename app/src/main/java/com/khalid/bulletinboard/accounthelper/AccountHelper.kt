package com.khalid.bulletinboard.accounthelper


import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.khalid.bulletinboard.MainActivity
import com.khalid.bulletinboard.R

class AccountHelper(activity: MainActivity) {
    val activity = activity

    fun signUpWithEmail(email:String,password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            activity.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if(task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)
                }else{
                    Toast.makeText(activity,activity.resources.getString(R.string.sign_up_erorr),Toast.LENGTH_SHORT).show()
                }

            }
        }


    }

    private fun sendEmailVerification(user:FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener { task->
            if (task.isSuccessful){
                Toast.makeText(activity,activity.resources.getString(R.string.send_verification_done),Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity,activity.resources.getString(R.string.send_verification_error),Toast.LENGTH_SHORT).show()
            }
        }
    }
}