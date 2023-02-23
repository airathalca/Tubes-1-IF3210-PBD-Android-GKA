package com.example.majika.ui.twibbon

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.majika.databinding.FragmentTwibbonBinding
import kotlinx.android.synthetic.main.fragment_twibbon.*

class TwibbonFragment : Fragment() {

    private var _binding: FragmentTwibbonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var imageCapture: ImageCapture? = null

    private var previewFrozen: Boolean = false
    private var cameraProvider: ProcessCameraProvider? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTwibbonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // Do if the permission is granted
                startCamera()
            }
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)

        imageCapture = ImageCapture.Builder().build()

        binding.button.setOnClickListener {
            freezePreview()
        }

        return root
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({

            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                mPreview ->
                if (!previewFrozen) {
                    mPreview.setSurfaceProvider(binding.previewView.surfaceProvider)
                } else {
                    mPreview.setSurfaceProvider(null)
                }
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                cameraProvider?.unbindAll()
                cameraProvider?.bindToLifecycle(this, cameraSelector, preview!!)
            } catch (e: Exception) {
                Log.d("CameraX", "startCamera Failed:", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun freezePreview() {
        previewFrozen = !previewFrozen
        if (!previewFrozen) {
            button.text = "Capture"
        } else {
            button.text = "Retake"
        }
        startCamera()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}