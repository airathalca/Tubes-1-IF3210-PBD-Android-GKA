package com.example.majika.ui.foodBank

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.databinding.FragmentFoodBankBinding
import com.example.majika.repository.CartRepository
import com.example.majika.repository.Repository
import com.example.majika.room.CartDatabase
import com.example.majika.ui.shoppingCart.ShoppingCartViewModel
import com.example.majika.ui.shoppingCart.ShoppingCartViewModelFactory

class FoodBankFragment : Fragment(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var mTempSensor: Sensor

    private val menuAdapter by lazy { MenuAdapter() }
    private var _binding: FragmentFoodBankBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuList.layoutManager = LinearLayoutManager(context)
        binding.menuList.adapter = menuAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cartDatabase = CartDatabase.getDatabase(requireContext())
        val cartRepository = CartRepository(cartDatabase)
        val cartModelFactory = ShoppingCartViewModelFactory(cartRepository)
        val cartViewModel =
            ViewModelProvider(this, cartModelFactory)[ShoppingCartViewModel::class.java]
        val repository = Repository()
        val viewModelFactory = FoodBankViewModelFactory(repository)
        val foodBankViewModel =
            ViewModelProvider(this, viewModelFactory)[FoodBankViewModel::class.java]
        foodBankViewModel.getMenus()
        foodBankViewModel.menuRes.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.data.let {
                    if (it != null) {
                        menuAdapter.showData(it, cartViewModel)
                    }
                }
            }
        })
        _binding = FragmentFoodBankBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mSensorManager = requireActivity().getSystemService(SensorManager::class.java)
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            mSensorManager.registerListener(this, mTempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            binding.textTemperature.text = "23°C"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(p0: SensorEvent) {
        if (p0.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            binding.textTemperature.text = String.format("%.1f°C", p0.values[0])
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Do nothing
    }
}