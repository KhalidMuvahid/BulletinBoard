package com.khalid.bulletinboard.dialoghelper

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.khalid.bulletinboard.MainActivity
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.accounthelper.AccountHelper
import com.khalid.bulletinboard.databinding.SignDialogBinding

class DialogHelper(activity:MainActivity) {
    private val activity = activity
    val accountHelper = AccountHelper(activity)

    fun createSignDialog(state:Int){
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        builder.setView(binding.root)

        setDialogState(state,binding)

        var dialog = builder.create()

        binding.btSignUpIn.setOnClickListener {
            setOnClickSignUpIn(dialog,binding,state)
            dialog.dismiss()
        }

        binding.btForget.setOnClickListener {
            setOnClickResetPassword(binding,activity,dialog)
        }

        binding.btGoogleSignIn.setOnClickListener {
            accountHelper.signInWithGoogle()
            dialog.dismiss()
        }


        dialog.show()
    }

    private fun setOnClickResetPassword(binding: SignDialogBinding,activity: MainActivity,dialog: AlertDialog) {
        if (binding.edSignEmail.text.isNotEmpty()){
            activity.mAuth.sendPasswordResetEmail(binding.edSignEmail.text.toString()).addOnCompleteListener {task->
                if (task.isSuccessful){
                    Toast.makeText(activity,R.string.reset_message,Toast.LENGTH_LONG).show()
                }
            }
            dialog.dismiss()
        }else{
            binding.tvDialogMessage.visibility = View.VISIBLE
        }


    }

    private fun setOnClickSignUpIn(dialog: AlertDialog, binding: SignDialogBinding,state: Int) {
        dialog.dismiss()
        if (state == DialogConst.SING_UP_STATE){
            accountHelper.signUpWithEmail(binding.edSignEmail.text.toString(),binding.edSignPassword.text.toString())
        }else {
            accountHelper.signInWithEmail(binding.edSignEmail.text.toString(),binding.edSignPassword.text.toString())
        }
    }

    private fun setDialogState(state: Int, binding: SignDialogBinding) {
        if (state == DialogConst.SING_UP_STATE){
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_up)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_up_action)
        }else{
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_in)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_in_action)
            binding.btForget.visibility = View.VISIBLE
        }
    }
}