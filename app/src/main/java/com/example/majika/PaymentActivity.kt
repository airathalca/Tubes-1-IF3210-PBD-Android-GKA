package com.example.majika

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.majika.databinding.ActivityPaymentBinding
import com.example.majika.ui.payment.PaymentFragment

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = PaymentFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholderPayment, fragment)
            .commit()
    }
}