package com.khalid.bulletinboard.accounthelper


import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.*
import com.khalid.bulletinboard.MainActivity
import com.khalid.bulletinboard.R
import com.khalid.bulletinboard.constants.FirebaseAuthConst

const val REQUEST_SIGN_IN_CODE = 132
class AccountHelper(activity: MainActivity) {
    val activity = activity
    lateinit var signeInClient:GoogleSignInClient

    fun signUpWithEmail(email:String,password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            activity.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if(task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)
                    activity.navHeaderUpdate(task.result?.user)
                }else{
                    Toast.makeText(activity,activity.resources.getString(R.string.sign_up_error),Toast.LENGTH_SHORT).show()
                    Log.i("MyLog","${task.exception}")
                    if(task.exception is FirebaseAuthUserCollisionException){
                        var exception = task.exception as FirebaseAuthUserCollisionException
                        if(exception.errorCode == FirebaseAuthConst.ERROR_EMAIL_ALREADY_IN_USE){
                            Toast.makeText(activity,"Этот аккаунт уже зарегистрирован",Toast.LENGTH_LONG).show()
                        }
                    }else if(task.exception is FirebaseAuthInvalidCredentialsException){
                        var exception = task.exception as FirebaseAuthInvalidCredentialsException
                        Log.i("MyLog","${exception.errorCode}")
                        if(exception.errorCode == FirebaseAuthConst.ERROR_INVALID_EMAIL){
                            Toast.makeText(activity,"Введен не правильный аддрес электроной почты",Toast.LENGTH_LONG).show()
                        }else if (exception.errorCode == FirebaseAuthConst.ERROR_WEAK_PASSWORD){
                            Toast.makeText(activity,"Пароль не должен быть меньше 6 символов",Toast.LENGTH_LONG).show()
                        }
                    }

                }

            }
        }
    }

    fun signInWithEmail(email:String,password:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            activity.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(activity,activity.resources.getString(R.string.sign_in_done),Toast.LENGTH_SHORT).show()
                    activity.navHeaderUpdate(task.result?.user)
                }else{
                    Toast.makeText(activity,activity.resources.getString(R.string.sign_in_error),Toast.LENGTH_SHORT).show()
                    Log.i("MyLog","${task.exception}")
                    if(task.exception is FirebaseAuthInvalidCredentialsException){
                        var exception = task.exception as FirebaseAuthInvalidCredentialsException
                        Log.i("MyLog","${exception.errorCode}")
                        if(exception.errorCode == FirebaseAuthConst.ERROR_INVALID_EMAIL){
                            Toast.makeText(activity,"Введен не правильный аддрес электроной почты",Toast.LENGTH_LONG).show()
                        }else if(exception.errorCode == FirebaseAuthConst.ERROR_WRONG_PASSWORD){
                            Toast.makeText(activity,"Введен не правильный пароль",Toast.LENGTH_LONG).show()
                        }
                    }else if(task.exception is FirebaseAuthInvalidUserException){
                        var exception = task.exception as FirebaseAuthInvalidUserException
                        Log.i("MyLog","${exception.errorCode}")
                        if(exception.errorCode == FirebaseAuthConst.ERROR_USER_NOT_FOUND){
                            Toast.makeText(activity,"Пользователь с данными параметрами не был найден",Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        }
    }

    private fun getSignInClient():GoogleSignInClient{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id)).requestEmail().build()
        return GoogleSignIn.getClient(activity,gso)
    }

    fun signInWithGoogle(){
        signeInClient = getSignInClient()
        var intent = signeInClient.signInIntent
        activity.startActivityForResult(intent,REQUEST_SIGN_IN_CODE)
    }

    fun signInFireBaseWithGoogle(token:String){
        val credential = GoogleAuthProvider.getCredential(token,null)
        activity.mAuth.signInWithCredential(credential).addOnCompleteListener {task->
            if (task.isSuccessful){
                activity.navHeaderUpdate(task.result?.user)
                Toast.makeText(activity,"Sign in done",Toast.LENGTH_LONG).show()
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