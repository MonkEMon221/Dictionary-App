package com.vishesh.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vishesh.dictionaryapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val KEY = "WORD_DEFINITION"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)

        binding.findButton.setOnClickListener {
            val url = getUrl()

            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener { response ->try {
                    extractDefinitionFromJson(response)
                }catch (exception:Exception){
                    exception.printStackTrace()
                    Toast.makeText(this,"Invalid Text",Toast.LENGTH_SHORT).show()
                }

                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringRequest)
        }

    }

    private fun getUrl():String{
        val word = binding.wordEditText.text
        val apiKey = "d0b99e5e-aea1-4f59-a1b0-51eee614e221"
        val url =
            "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
        return url
    }

    private fun extractDefinitionFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(KEY,firstShortDefinition.toString())
        startActivity(intent)
    }
}