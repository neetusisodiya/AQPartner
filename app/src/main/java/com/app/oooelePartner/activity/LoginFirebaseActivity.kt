package com.app.oooelePartner.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.oooelePartner.Bean.LoginBean
import com.app.oooelePartner.Prefrence.AppPreferences
import com.app.oooelePartner.R
import com.app.oooelePartner.Response.ResponseLogin
import com.app.oooelePartner.Rest.ApiClient
import com.app.oooelePartner.Rest.ApiInterface
import com.google.android.gms.tasks.TaskExecutors
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.iid.FirebaseInstanceId
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.wang.avi.AVLoadingIndicatorView
import maes.tech.intentanim.CustomIntent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


class LoginFirebaseActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mobile: String
    var otp: String? = null
    var loginBean: LoginBean? = null

    var number: EditText? = null
    var mobile_otp: EditText? = null
    var message: String? = null
    var enter_btn: Button? = null
    var btnenter_otp: Button? = null
    var txt: TextView? = null
    var tvResend: TextView? = null
    var bar: AVLoadingIndicatorView? = null
    var mAppPreferences: AppPreferences? = null
    var tvWait: TextView? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance();
        mAppPreferences = AppPreferences(this)
        secureToken = if (mAppPreferences!!.accessToken == null) ({
            FirebaseInstanceId.getInstance().token
        }.toString()) else {
            mAppPreferences!!.accessToken
        }
        find()
        /*  startSmsUserConsent()*/
    }

    var TAG = "LOG_MESSAGE"
    lateinit var mVerificationId: String
    lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    fun find() {
        bar = findViewById(R.id.bar)
        txt = findViewById(R.id.txt)
        number = findViewById(R.id.mobile_txt)
        enter_btn = findViewById(R.id.enter_btn)
        mobile_otp = findViewById(R.id.mobile_otp)
        btnenter_otp = findViewById(R.id.enter_otp)
        tvWait = findViewById(R.id.wait_txt)
        tvResend = findViewById(R.id.resend_otp)
        tvWait?.visibility = View.GONE
        btnenter_otp?.setOnClickListener(this)
        enter_btn?.setOnClickListener(this)
        tvResend?.setOnClickListener {


        }
/*
        mobile_otp?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
              */
/*  if (mobile_otp?.text.toString() == otp) {
                    bar?.visibility = View.VISIBLE
                    btnenter_otp?.isClickable = false


                }*//*

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
*/
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Log.d(TAG, "onVerificationCompleted:$credential")
                val code: String? = credential.smsCode
                code?.let { verifyVerificationCode(it) };
                doLogin()
            }

            override fun onVerificationFailed(e: FirebaseException) {

                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                } else if (e is FirebaseTooManyRequestsException) {

                }


            }

            override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
            ) {
                mVerificationId = verificationId;

                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")


            }
        }
    }

    private fun verifyVerificationCode(code: String) {
        //creating the credential
        val credential = PhoneAuthProvider.getCredential(mVerificationId, code)

        //signing the user
      //  signInWithPhoneAuthCredential(credential)


    }

    private fun loginUser() {
        AppPreferences.saveInPref(this@LoginFirebaseActivity, loginBean)
        val appPreferences = AppPreferences(this@LoginFirebaseActivity)
        appPreferences.setUserData(AppPreferences.KEY_ID, loginBean!!.id)
        appPreferences.setUserData(AppPreferences.NAME, loginBean!!.name)
        appPreferences.setUserData(AppPreferences.PHONE_NUMBER, loginBean!!.mobno)
        appPreferences.setUserData(AppPreferences.EMAIL, loginBean!!.email)
        appPreferences.setUserData(AppPreferences.Name_In_Bank, loginBean!!.bank_name)
        appPreferences.setUserData(AppPreferences.bankIfscCode, loginBean!!.ifsc)
        appPreferences.setUserData(AppPreferences.Account_Number, loginBean!!.account_no)
        appPreferences.setUserData(AppPreferences.DOB, loginBean!!.dob)
        appPreferences.setUserData(AppPreferences.PAN_CARD, loginBean!!.pancard_no)
        appPreferences.setUserData(AppPreferences.secure_token, loginBean!!.secure_token)
        appPreferences.setUserData(AppPreferences.ADDRESS, loginBean!!.address)
        appPreferences.setUserData(AppPreferences.PINCODE, loginBean!!.pincode)
        appPreferences.setUserData(AppPreferences._isFirstTime, loginBean!!.status)
        if (loginBean!!.exp_year != null) appPreferences.setUserData(AppPreferences.EXPERIENCE_YEAR, loginBean!!.exp_year)
        if (loginBean!!.exp_month != null) appPreferences.setUserData(AppPreferences.EXPERIENCE_MONTH, loginBean!!.exp_month)
        if (loginBean!!.qualification_id != null) appPreferences.setUserData(AppPreferences.Qualifications, loginBean!!.qualification_id.toString())
        if (loginBean!!.expert_type_id != null) appPreferences.setUserData(AppPreferences.EXPERT_IN, loginBean!!.expert_type_id.toString())
        startActivity(Intent(this@LoginFirebaseActivity, MainActivity::class.java))
        finish()
        CustomIntent.customType(this@LoginFirebaseActivity, "left-to-right")
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential).addOnCompleteListener(this@LoginFirebaseActivity) { task ->
            if (task.isSuccessful) {
                //verification successful we will start the profile activity
                /* val intent = Intent(this@LoginFirebaseActivity, ProfileActivity::class.java)
                 intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                 startActivity(intent)*/
               // doLogin()
            } else {

                //verification unsuccessful.. display an error message
                var message = "Something is wrong, we will fix it soon..."
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    message = "Invalid code entered..."
                }
                val snackbar: Snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG)
                snackbar.setAction("Dismiss") { }
                snackbar.show()
            }
        }
    }

    var secureToken: String? = null
    private fun doLogin() {
        bar!!.visibility = View.VISIBLE
        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val builder = ApiClient.createBuilder(arrayOf("mobno", "secure_token"), arrayOf(mobile, secureToken))
        val call = apiInterface.ApiLogin(builder.build())
        bar!!.visibility = View.GONE
        val callback = object : Callback<ResponseLogin> {
            override fun onFailure(call: Call<ResponseLogin>?, t: Throwable?) {
                Toast.makeText(this@LoginFirebaseActivity, t!!.message, Toast.LENGTH_SHORT).show()
                btnenter_otp!!.isClickable = true
            }

            override fun onResponse(call: Call<ResponseLogin>?, response: Response<ResponseLogin>?) {
                response?.isSuccessful.let {
                    if (response!!.body()!!.isStatus) {
                        /*  otp = response.body()!!.otp
                          Log.d("LOG_MESSAGE", "getOTP: $otp")*/
                        bar!!.visibility = View.GONE
                        tvWait!!.visibility = View.VISIBLE
                        txt!!.text = "Please Enter 6 digit Otp here"
                        number!!.visibility = View.GONE
                        enter_btn!!.visibility = View.GONE
                        mobile_otp!!.visibility = View.VISIBLE
                        btnenter_otp!!.visibility = View.VISIBLE
                        tvResend!!.visibility = View.VISIBLE
                        loginBean = response.body()!!.data
                        loginUser()

                    } else {
                        btnenter_otp!!.isClickable = true
                        DynamicToast.makeError(applicationContext,
                                "The username or password is incorrect").show()
                    }
                }
            }
        }
        call.enqueue(callback)

    }

    override fun onClick(v: View?) {
        if (v == enter_btn) {
            mobile = number?.text.toString();
            if (mobile.length == 10) {

                bar!!.visibility = View.VISIBLE

                sendVerificationCode(mobile);

//                doLogin();
            } else {
                DynamicToast.makeError(applicationContext, "Enter 10 digit number").show();

            }
        }
        if (v == btnenter_otp) {
            val tvText: TextView = findViewById(R.id.mobile_otp)
            val _otp = tvText.getText().toString().trim()
            btnenter_otp?.text = "Submit";
            if (_otp == otp) {
                //loginUser();
            } else {
                Toast.makeText(this, " please fill valid OTP number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun sendVerificationCode(mobile: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91$mobile",
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks)
    }

}