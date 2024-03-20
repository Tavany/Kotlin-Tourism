package com.eja.tugasbesar

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.eja.tugasbesar.adapter.GridMyDataAdapter
import com.eja.tugasbesar.data.fragment.fragment_explore
import com.eja.tugasbesar.data.fragment.fragment_home
import com.eja.tugasbesar.data.fragment.fragment_profile
import com.eja.tugasbesar.data.fragment.fragment_wishlist
import com.eja.tugasbesar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val fragmentHome: Fragment = fragment_home()
    val fragmentExplore: Fragment = fragment_explore()
    val fragmentWishlist: Fragment = fragment_wishlist()
    val fragmentProfile: Fragment = fragment_profile()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragmentHome

    lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var binding: ActivityMainBinding
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
//        hideMyKeyboard()
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
////
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_explore, R.id.navigation_wishlist, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        buttonNavigation()

//        binding.rvMydata.setHasFixedSize(true)
//        list.addAll(getListMyDatas())

    }

    private fun buttonNavigation() {
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentExplore).hide(fragmentExplore).commit()
        fm.beginTransaction().add(R.id.container, fragmentWishlist).hide(fragmentWishlist).commit()
        fm.beginTransaction().add(R.id.container, fragmentProfile).hide(fragmentProfile).commit()

        bottomNavigationView = binding.navView
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_explore -> {
                    callFragment(1, fragmentExplore)
                }
                R.id.navigation_wishlist -> {
                    callFragment(2, fragmentWishlist)
                }
                R.id.navigation_profile -> {
                    callFragment(3, fragmentProfile)
                }
            }
            false
        }
    }

    private fun callFragment(index : Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }

//    private fun showRecyclerGrid() {
//        binding.apply {
//            rvMydata.layoutManager = GridLayoutManager(this@MainActivity, 2)
//            val gridMyDataAdapter = GridMyDataAdapter(list)
//            rvMydata.adapter = gridMyDataAdapter
//        }
//    }

//    fun getListMyDatas(): ArrayList<MyData> {
//        val dataName = resources.getStringArray(R.array.data_name)
//        val dataDescription = resources.getStringArray(R.array.data_description)
//        val dataPhoto = resources.getStringArray(R.array.data_photo)
//        val listMyData = ArrayList<MyData>()
//        for (position in dataName.indices) {
//            val myData = MyData(
//                dataName[position],
//                dataDescription[position],
//                dataPhoto[position]
//            )
//            listMyData.add(myData)
//        }
//        return listMyData
//    }

}