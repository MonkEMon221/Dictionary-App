package com.vishesh.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vishesh.dictionaryapp.databinding.ActivityDefinitionBinding
import com.vishesh.dictionaryapp.databinding.ActivityMainBinding

class DefinitionActivity : AppCompatActivity() {
    private val KEY = "WORD_DEFINITION"
    lateinit var binding: ActivityDefinitionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        intent.getStringExtra(KEY)
        binding.definitionTextView.text = intent.getStringExtra(KEY)
        binding.backImageView.setOnClickListener { finish() }
    }
}