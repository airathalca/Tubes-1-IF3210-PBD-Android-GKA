package com.example.majika.ui.foodBank

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.majika.R
import com.example.majika.databinding.FragmentFoodBankBinding
import com.example.majika.models.Menu
import com.example.majika.repository.CartRepository
import com.example.majika.repository.Repository
import com.example.majika.room.CartDatabase
import com.example.majika.ui.shoppingCart.ShoppingCartViewModel
import com.example.majika.ui.shoppingCart.ShoppingCartViewModelFactory

class FoodBankFragment : Fragment(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var mTempSensor: Sensor
    private lateinit var data: ArrayList<Menu>
    private var _binding: FragmentFoodBankBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuList.layoutManager = LinearLayoutManager(context)
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
        foodBankViewModel.menuRes.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.data.let { it ->
                    if (it != null) {
                        it.sortByDescending { it.type }
                        val menuAdapter = MenuAdapter()
                        menuAdapter.showData(it, cartViewModel)
                        binding.menuList.adapter = menuAdapter
                        // save it to data
                        data = it
                    }
                }
            }
        }

        _binding = FragmentFoodBankBinding.inflate(inflater, container, false)

        mSensorManager = requireActivity().getSystemService(SensorManager::class.java)
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            mTempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            mSensorManager.registerListener(this, mTempSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            binding.textTemperature.text = "0°C"
        }

        binding.searchLayout.editText?.afterTextChanged { updateData(it, cartViewModel) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSensorChanged(p0: SensorEvent) {
        if (p0.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            activity?.findViewById<TextView>(R.id.text_temperature)?.text = String.format("%d°C", p0.values[0].toInt())
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        // Do nothing
    }

    private fun updateData(query: String, cartViewModel: ShoppingCartViewModel) {
        val temp = ArrayList(data.filter { it.name.contains(query, ignoreCase = true) })
        val menuAdapter = MenuAdapter()
        menuAdapter.showData(temp, cartViewModel)
        binding.menuList.adapter = menuAdapter
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}