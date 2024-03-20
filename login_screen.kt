package com.eja.tugasbesar

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
//import com.eja.tugasbesar.SmsReceiverActivity.Companion.EXTRA_SMS_REGIST
import com.eja.tugasbesar.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    private val binding: ActivityLoginScreenBinding by lazy {
        ActivityLoginScreenBinding.inflate(layoutInflater)
    }

//    private lateinit var settingModel: SettingModel
//    private lateinit var mSettingPreference: SettingPreference
//
//    private val resultLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.data != null && result.resultCode == SettingPreferenceActivity.RESULT_CODE) {
//                settingModel =
//                    result.data?.parcelable<SettingModel>(SettingPreferenceActivity.EXTRA_RESULT) as SettingModel
////                populateView(settingModel)
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        supportActionBar?.title = getString(R.string.main_title)
//        mSettingPreference = SettingPreference(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.RECEIVE_SMS),
                SMS_REQUEST_CODE
            )
            }

        binding.btnPage.setOnClickListener{
            val intent = Intent(this, activity_loading::class.java)
            startActivity(intent)
        }

//        binding.btnReg.setOnClickListener {
//            val intent = Intent(this, ActivityRegisterBinding::class.java)
//            intent.putExtra("SETTING", settingModel)
//            resultLauncher.launch(intent)
//        }

        binding.apply{
            btnPage.setOnClickListener {
                val fullName = textInputFullName.editText?.text.toString()
                val email = textInputEmail.editText?.text.toString()
                val password = textInputPassword.editText?.text.toString()
                if (fullName.isEmpty()) {
                    textInputFullName.error = "Full Name cannot be empty!"
                }
                if (email.isEmpty()) {
                    textInputEmail.error = "Email cannot be empty!"
                }
                if (password.isEmpty()) {
                    textInputPassword.error = "Password cannot be empty!"
                }

                else {

                    val isDetect = intent.getBooleanExtra(EXTRA_SMS_REGIST, false)
                    if (isDetect) {
                        val moveWithDataIntent = Intent(this@LoginScreen, activity_loading::class.java)
                        moveWithDataIntent.putExtra(activity_loading.FULL_NAME, fullName)
                        startActivity(moveWithDataIntent)
                    }
                }

                return@setOnClickListener
            }
        }
//        showExistingPreferences()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults:
    IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST_CODE) {
            when (PackageManager.PERMISSION_GRANTED) {
                grantResults[0] -> Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
//        Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
//        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
//    }
//
//    private fun showExistingPreferences() {
//        settingModel = mSettingPreference.getSetting()
////        populateView(settingModel)
//    }

//    private fun populateView(settingModel: SettingModel) {
//        with(binding) {
//            inputFullname.text = settingModel.name.toString().ifEmpty { getString(R.string.empty_message) }
////            tvNim.text = settingModel.nim.toString().ifEmpty { getString(R.string.empty_message) }
////            tvGender.text =
////                if (settingModel.gender) getString(R.string.laki) else getString(R.string.perempuan)
//            inputEmail.text =
//                settingModel.email.toString().ifEmpty { getString(R.string.empty_message) }
//            inputPassword.text =
//                settingModel.email.toString().ifEmpty { getString(R.string.empty_message) }
////            tvPhone.text =
////                settingModel.phoneNumber.toString().ifEmpty { getString(R.string.empty_message) }
////            tvAge.text = settingModel.age.toString().ifEmpty { getString(R.string.empty_message) }
////            tvTheme.text =
////                if (settingModel.isDarkTheme) getString(R.string.dark) else getString(R.string.light)
//        }
//    }

    companion object {
        private const val SMS_REQUEST_CODE = 101

        const val EXTRA_SMS_REGIST = "extra_sms_regist"
    }
}