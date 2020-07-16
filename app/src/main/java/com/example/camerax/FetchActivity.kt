package com.example.camerax

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.CameraXThreads.TAG
import com.example.camerax.MainActivity.Companion.TAG
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_fetch.*
import java.io.File
import java.io.IOException



class FetchActivity : AppCompatActivity() {


    private var storage = Firebase.storage
    var storageRef= storage.reference

    private val TAG = String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById< Button>(R.id.button)

        storageRef = Firebase.storage.getReferenceFromUrl("gs://camerax-40c7f.appspot.com/Images").child("$photos")
        button.setOnClickListener {
                try {
                    val file: File = File.createTempFile("image", "jpg")
                    storageRef.getFile(file).addOnSuccessListener {
                        val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
                        imageView.setImageBitmap(bitmap)
                    }.addOnFailureListener { Toast.makeText(this@FetchActivity, "Image Failed to load", Toast.LENGTH_SHORT).show() }
                }
                catch (e: IOException) {
                    e.printStackTrace()
                }
            Log.e(TAG.toString(), "failed")

        }

    }

    }

