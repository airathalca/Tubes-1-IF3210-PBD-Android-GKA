package com.example.majika

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.majika.databinding.ActivityMainBinding
import com.example.majika.ui.navbar.NavBarFragment
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

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

        KeyboardVisibilityEvent.setEventListener(
            this
        ) {
            if (it) {
                binding.placeholderNavBar.visibility = View.GONE
            } else {
                binding.placeholderNavBar.visibility = View.VISIBLE
            }
        }
    }
}
