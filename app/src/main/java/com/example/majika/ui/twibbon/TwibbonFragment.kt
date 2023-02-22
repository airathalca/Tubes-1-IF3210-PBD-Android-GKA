package com.example.majika.ui.twibbon

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.majika.databinding.FragmentTwibbonBinding

class TwibbonFragment : Fragment() {

    private var _binding: FragmentTwibbonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var imageCapture: ImageCapture? = null

    private var previewFrozen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val twibbonViewModel =
            ViewModelProvider(this).get(TwibbonViewModel::class.java)

        _binding = FragmentTwibbonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // Do if the permission is granted
                Toast.makeText(requireContext(), "Permission granted", Toast.LENGTH_SHORT).show()

                startCamera()
            }
            else {
                // Do otherwise
            }
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)

        imageCapture = ImageCapture.Builder().build()

        binding.button.setOnClickListener {
//            freezePreview()
        }
        return root
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                mPreview ->
                if (!previewFrozen) {
                    mPreview.setSurfaceProvider(binding.previewView.surfaceProvider)
                }
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.d("CameraX", "startCamera Failed:", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}