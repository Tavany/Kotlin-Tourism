package com.eja.tugasbesar.data.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.eja.tugasbesar.MainActivity
import com.eja.tugasbesar.MyData
import com.eja.tugasbesar.R
import com.eja.tugasbesar.SettingPreference
import com.eja.tugasbesar.adapter.GridMyDataAdapter
import com.eja.tugasbesar.databinding.FragmentHomeBinding
import com.eja.tugasbesar.databinding.FragmentProfileBinding


class fragment_home : Fragment() {


    private val list = ArrayList<MyData>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {


            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            return binding?.root

        }
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(binding.root)
////
////        supportActionBar?.title = getString(R.string.main_title)
////        mSettingPreference = SettingPreference(this)
////
////        showExistingPreferences()
////    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding?.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMydata?.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerGrid()

    }

    private fun showRecyclerGrid() {
        binding?.apply {
            rvMydata.layoutManager = GridLayoutManager(context, 2)
            val gridMyDataAdapter = GridMyDataAdapter(list)
            rvMydata.adapter = gridMyDataAdapter
        }
    }


    private fun getListMyDatas(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataLat = resources.getStringArray(R.array.data_lat)
        val dataLang = resources.getStringArray(R.array.data_lang)
        val listMyData = ArrayList<MyData>()
        for (position in dataName.indices) {
            val myData = MyData(
                dataName[position],
                dataDescription[position],
                dataPhoto.getResourceId(position, -1),
                dataLat[position].toDouble(),
                dataLang[position].toDouble()
            )
            listMyData.add(myData)
        }
        return listMyData
    }
}