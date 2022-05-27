package com.khalid.bulletinboard.dialoghelper

import androidx.appcompat.app.AlertDialog
import com.khalid.bulletinboard.MainActivity
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.accounthelper.AccountHelper
import com.khalid.bulletinboard.databinding.SignDialogBinding

class DialogHelper(activity:MainActivity) {
    private val activity = activity
    private val accountHelper = AccountHelper(activity)

    fun createSignDialog(state:Int){
        val builder = AlertDialog.Builder(activity)
        val binding = SignDialogBinding.inflate(activity.layoutInflater)
        builder.setView(binding.root)

        if (state == DialogConst.SING_UP_STATE){
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_up)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_up_action)
        }else{
            binding.tvSignTitle.text = activity.resources.getString(R.string.ac_sign_in)
            binding.btSignUpIn.text = activity.resources.getString(R.string.sign_in_action)
        }

        var dialog = builder.create()
        binding.btSignUpIn.setOnClickListener {
            dialog.dismiss()
            if (state == DialogConst.SING_UP_STATE){
                accountHelper.signUpWithEmail(binding.edSignEmail.text.toString(),binding.edSignPassword.text.toString())
            }else {
                accountHelper.signInWithEmail(binding.edSignEmail.text.toString(),binding.edSignPassword.text.toString())
            }
        }
        dialog.show()
    }
}