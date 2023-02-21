package com.example.majika

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.majika.databinding.ActivityMainBinding
import com.example.majika.ui.navbar.NavBarFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = NavBarFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholderNavBar, fragment)
            .commit()
    }
}
