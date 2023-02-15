package com.example.majika

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.majika.databinding.ActivityMainBinding
import com.example.majika.ui.navbar.NavBarFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "Daftar Menu"
        

        val fragment = NavBarFragment()
        supportFragmentManager.beginTransaction().replace(R.id.placeholderNavBar, fragment).commit()
    }
}
