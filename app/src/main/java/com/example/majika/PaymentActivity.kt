package com.example.majika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.majika.models.MenuRes
import com.example.majika.models.PaymentRes
import com.example.majika.repository.Repository
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Response

class PaymentActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, View.OnClickListener {
    private lateinit var mScannerView: ZXingScannerView
    private lateinit var repository: Repository
    private var isCaptured = false
    private var paymentStatus = false
    val paymentRes: MutableLiveData<Response<PaymentRes>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        initScannerView()
        initDefaultView()
        button_reset.setOnClickListener(this)
    }

    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    private fun initScannerView() {
        mScannerView = ZXingScannerView(this)
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    private fun initDefaultView() {
        text_view_qr_code_value.text = "QR Code Value"
        button_reset.visibility = View.GONE
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun doPayment(code: String) = GlobalScope.launch {
        val response = repository.doPayment(code)
        paymentRes.value = response
    }

    override fun handleResult(rawResult: Result?) {
        text_view_qr_code_value.text = rawResult?.text
        button_reset.visibility = View.VISIBLE
        if (rawResult != null) {
            doPayment(rawResult.text)
            paymentRes.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body()?.status.let {
                        if (it == "SUCCESS") {
                            text_view_qr_code_value.text = "Payment success"
                        } else if (it == "FAILED") {

                        } else {

                        }
                    }
                }
            })
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_reset -> {
                mScannerView.resumeCameraPreview(this)
                initDefaultView()
            }
        }

    }
}