package com.example.webtoon

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.webtoon.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name", "월요웹툰")
        val tab1 = sharedPreference?.getString("tab1_name", "화요웹툰")
        val tab2 = sharedPreference?.getString("tab2_name", "수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if(currentFragment is WebViewFragment) {
            if(currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }

}