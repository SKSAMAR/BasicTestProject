package sk.samar.testproject.presentation.postDetailed

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import sk.samar.testproject.databinding.FragmentDetailsBinding
import sk.samar.testproject.domain.model.PostModel
import sk.samar.testproject.util.Constants
import sk.samar.testproject.util.Constants.ACCESSIBILITY_SERVICE_PERMISSION_REQUEST

class PostDetailedFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val post = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(Constants.Post, PostModel::class.java)
        } else {
            arguments?.getParcelable(Constants.Post) as PostModel?
        }
        post?.let {
            binding.postModel = post
        }

        binding.startWhatsApp.setOnClickListener {
            val intent1: Intent? =  requireActivity().packageManager.getLaunchIntentForPackage("com.whatsapp")
            startActivity(intent1)
        }

        binding.getPermissionWhatsApp.setOnClickListener {
            if (!isAccessibilityServicePermissionGranted()) {
                requestAccessibilityServicePermission()
                startMyAccessibilityService()
            } else {
                startMyAccessibilityService()
            }
        }

    }


    private fun isAccessibilityServicePermissionGranted(): Boolean {
        val accessibilityServicePermission = Manifest.permission.BIND_ACCESSIBILITY_SERVICE
        val granted = PackageManager.PERMISSION_GRANTED
        return ContextCompat.checkSelfPermission(
            requireContext(),
            accessibilityServicePermission
        ) == granted
    }

    private fun requestAccessibilityServicePermission() {
        val accessibilityServicePermission = Manifest.permission.BIND_ACCESSIBILITY_SERVICE
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(accessibilityServicePermission),
            ACCESSIBILITY_SERVICE_PERMISSION_REQUEST
        )
    }

    private fun startMyAccessibilityService() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESSIBILITY_SERVICE_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                startMyAccessibilityService()

            } else {
                // Permission denied
                Toast.makeText(
                    requireContext(),
                    "Accessibility Service permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}