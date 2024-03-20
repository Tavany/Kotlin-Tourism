package com.eja.tugasbesar.data.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.eja.tugasbesar.*
import com.eja.tugasbesar.SettingPreference
import com.eja.tugasbesar.databinding.FragmentProfileBinding

class fragment_profile : Fragment() {
    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private lateinit var settingModel: SettingModel
    private lateinit var mSettingPreference: SettingPreference

    private var _binding: FragmentProfileBinding? = null


    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data != null && result.resultCode == SettingPreferenceActivity.RESULT_CODE) {
                settingModel =
                    result.data?.parcelable<SettingModel>(SettingPreferenceActivity.EXTRA_RESULT) as SettingModel
                populateView(settingModel)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPage.setOnClickListener {
            val intent = Intent(requireContext(), SettingPreferenceActivity::class.java)
            intent.putExtra("SETTING", settingModel)
            resultLauncher.launch(intent)
            showToast()
        }

        (activity as MainActivity).supportActionBar?.title = "Title"
        mSettingPreference = SettingPreference(requireContext())

        showExistingPreferences()
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Button clicked!", Toast.LENGTH_SHORT).show()
    }

    private inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
    }

    private fun showExistingPreferences() {
        settingModel = mSettingPreference.getSetting()
        populateView(settingModel)
    }

    private fun populateView(settingModel: SettingModel) {

        val activity = activity as? AppCompatActivity

        with(binding) {
            tvName.text = settingModel.name.toString().ifEmpty { getString(R.string.empty_message) }
            tvNim.text = settingModel.nim.toString().ifEmpty { getString(R.string.empty_message) }
            tvGender.text =
                if (settingModel.gender) getString(R.string.laki) else getString(R.string.perempuan)
            tvEmail.text =
                settingModel.email.toString().ifEmpty { getString(R.string.empty_message) }
            tvPhone.text =
                settingModel.phoneNumber.toString().ifEmpty { getString(R.string.empty_message) }
            tvAge.text = settingModel.age.toString().ifEmpty { getString(R.string.empty_message) }
            tvTheme.text =
                if (settingModel.isDarkTheme) getString(R.string.dark) else getString(R.string.light)
        }
        if (settingModel.isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            activity?.delegate?.applyDayNight()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            activity?.delegate?.applyDayNight()
        }
    }
}