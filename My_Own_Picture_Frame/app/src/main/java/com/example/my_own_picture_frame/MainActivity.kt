package com.example.my_own_picture_frame

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.my_own_picture_frame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val imageLoadLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            updateImages(uriList)
        }
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "사진 가져오기"
            setSupportActionBar(this)
        }

        binding.loadImageButton.setOnClickListener {
            checkPermission()
        }
        binding.navigateFrameActivityButton.setOnClickListener {
            navigateToFrameActivity()
        }

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                checkPermission()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun navigateToFrameActivity() {
        val images =
            imageAdapter.currentList.filterIsInstance<ImageItems.Image>().map { it.uri.toString() }
                .toTypedArray()
        val intent = Intent(this, FrameActivity::class.java)
            .putExtra("images", images)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
            override fun onLoadMoreClick() {
                checkPermission()
            }
        })

        binding.imageRecyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                showPermissionInfoDialog()
            }

            else -> {
                requestReadExternalStorage()
            }
        }
    }

    private fun showPermissionInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의") { _, _ ->
                requestReadExternalStorage()
            }
        }.show()
    }

    private fun loadImage() {
        imageLoadLauncher.launch("image/*")
    }

    private fun requestReadExternalStorage() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_EXTERNAL_STORAGE
        )
    }

    private fun updateImages(uriList: List<Uri>) {
        val images = uriList.map { ImageItems.Image(it) }
        val updatedImages = imageAdapter.currentList.toMutableList().apply { addAll(images) }
        imageAdapter.submitList(updatedImages)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_READ_EXTERNAL_STORAGE -> {
                val resultCode = grantResults.firstOrNull() ?: PackageManager.PERMISSION_GRANTED
                if (resultCode == PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
            }
        }
    }


    companion object {
        const val REQUEST_READ_EXTERNAL_STORAGE = 100
    }
}