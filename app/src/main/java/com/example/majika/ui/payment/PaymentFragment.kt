package com.example.majika.ui.payment

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.majika.MainActivity
import com.example.majika.databinding.FragmentPaymentBinding
import com.example.majika.repository.CartRepository
import com.example.majika.repository.Repository
import com.example.majika.room.CartDatabase
import com.example.majika.ui.shoppingCart.CartAdapter
import com.example.majika.ui.shoppingCart.ShoppingCartViewModel
import com.example.majika.ui.shoppingCart.ShoppingCartViewModelFactory
import kotlinx.android.synthetic.main.fragment_payment.*
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class PaymentFragment : Fragment(), ZXingScannerView.ResultHandler {
    private lateinit var mScannerView: ZXingScannerView
    private lateinit var paymentViewModel: PaymentViewModel
    private lateinit var cartViewModel: ShoppingCartViewModel

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private val cartAdapter by lazy { CartAdapter() }
    private var totalPrice = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mScannerView = ZXingScannerView(requireContext())
        doRequestPermission()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)

        val cartDatabase = CartDatabase.getDatabase(requireContext())
        val cartRepository = CartRepository(cartDatabase)
        val cartModelFactory = ShoppingCartViewModelFactory(cartRepository)
        cartViewModel =
            ViewModelProvider(this, cartModelFactory)[ShoppingCartViewModel::class.java]

        cartViewModel.listCart.observe(viewLifecycleOwner) { cartList ->
            if (cartList != null) {
                val cartArray = ArrayList(cartList)
                totalPrice = 0
                cartAdapter.setData(cartArray, cartViewModel)
                for (i in cartArray) {
                    totalPrice += i.price * i.quantity
                }

                binding.paymentPrice.text = String.format("Rp. %s", totalPrice.formatDecimalSeparator())
            }
        }

        val repository = Repository()
        val viewModelFactory = PaymentViewModelFactory(repository)
        paymentViewModel = ViewModelProvider(this, viewModelFactory)[PaymentViewModel::class.java]

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.setAutoFocus(true)
        mScannerView.startCamera()
    }

    private fun doRequestPermission() {
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // Do if the permission is granted
                frame_layout_camera.addView(mScannerView)
            } else {
                // Do otherwise
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    override fun handleResult(rawResult: Result) {
        paymentViewModel.doPayment(rawResult.text)
        paymentViewModel.paymentRes.observe(viewLifecycleOwner) { response ->
            val body = response.body()
            if (body?.status == "SUCCESS") {
                body?.status.let {
                    Log.d("Payment", body?.status!!)
                    text_view_qr_code_value.text = "Payment Success"
                    Toast.makeText(requireContext(), "Redirecting..", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        cartViewModel.deleteAllCart()

                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    }, 2000)
                }
            } else {
                val body = response.body()
                body?.status.let {
                    Log.d("Payment", body?.status!!)
                    text_view_qr_code_value.text = "Payment failed"
                    Toast.makeText(requireContext(), "Please try again", Toast.LENGTH_SHORT).show()
                    mScannerView.resumeCameraPreview(this)
                }
            }
        }
    }

    private fun Int.formatDecimalSeparator(): String {
        return toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }
}