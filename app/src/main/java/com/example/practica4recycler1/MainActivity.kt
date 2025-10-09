package com.example.practica4recycler1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica4recycler1package.ArticleAdapter

class MainActivity : AppCompatActivity() {
    lateinit var img1: ImageView
    lateinit var txt1: EditText
    lateinit var txt2: EditText
    lateinit var btn1: Button
    lateinit var recyclerView: RecyclerView
    private var imageUri: Uri? = null
    private val articles = mutableListOf<Article>()
    private lateinit var adapter: ArticleAdapter

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            img1.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img1 = findViewById(R.id.img1)
        txt1 = findViewById(R.id.txt1)
        txt2 = findViewById(R.id.txt2)
        btn1 = findViewById(R.id.btn1)
        recyclerView = findViewById(R.id.reciclerview1)

        adapter = ArticleAdapter(articles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        img1.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        btn1.setOnClickListener {
            val title = txt1.text.toString()
            val desc = txt2.text.toString()
            val uriStr = imageUri?.toString() ?: ""
            if (title.isNotEmpty() && desc.isNotEmpty() && uriStr.isNotEmpty()) {
                articles.add(Article(uriStr, title, desc))
                adapter.notifyItemInserted(articles.size - 1)
                txt1.text.clear()
                txt2.text.clear()
                img1.setImageResource(R.mipmap.ic_launcher_round)
                imageUri = null
            }
        }
    }
}
