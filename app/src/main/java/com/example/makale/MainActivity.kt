package com.example.makale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.makale.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5","Item 6")
    private val adapter = MainActivityAdapter(items)
    private val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val itemCount = adapter.itemCount

                if (lastVisiblePosition != RecyclerView.NO_POSITION && lastVisiblePosition == itemCount - 1) {
                    layoutManager.scrollToPosition(0)
                } else {
                    binding.recyclerView.smoothScrollBy(50, 0)
                }

                handler.postDelayed(this, 100)
            }
        }

        handler.postDelayed(runnable, 100)
    }
}