package com.example.patient.screens.scanPassport

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import com.example.patient.databinding.ScanPassportFragmentBinding
import com.example.patient.utils.base.BaseFragment
import java.io.File

class ScanPassportFragment : BaseFragment<ScanPassportFragmentBinding, ScanPassportViewModel>() {

    override fun getViewModelClass() = ScanPassportViewModel::class.java

    override fun getViewBinding(): ScanPassportFragmentBinding = ScanPassportFragmentBinding
        .inflate(layoutInflater)
    private val pickPicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
//            tmpFile?.let { uploadIdentificationImage(it) }
        }
    }
//    private val permissionManager = Per.from(this)
    private var photoURI: Uri? = null
    private var tmpFile: File? = null

    override fun observeData() {
        super.observeData()
    }

    override fun setUpViews() {
        super.setUpViews()
    }

    override fun observeView() {
        super.observeView()

    }
    private fun takePhoto(kindOfImg: String) {
//        docsViewModel.currantUploadImageName = kindOfImg
//
//        permissionManager.request(Permission.Camera).rationale("${getString(R.string.permission_camera)} ${getString(R.string.permission_camera_passport)}", R.drawable.permission_camera).checkPermission { success ->
//            if (success) {
//                tmpFile = ImageUtils.createImageFile(requireContext())
//                tmpFile.also {
//                    photoURI = FileProvider.getUriForFile(requireContext(), "${BuildConfig.APPLICATION_ID}.provider", it!!)
//                    this.pickPicture.launch(photoURI)
//                }
//            } else {
//                Utils.setUpPermission(requireContext())
//            }
//        }
    }
}