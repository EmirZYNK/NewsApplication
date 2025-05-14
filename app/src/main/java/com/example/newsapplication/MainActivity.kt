package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.BuildConfig
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.model.NewsResponse
import com.example.newsapplication.network.RetrofitInstance
import com.example.newsapplication.ui.WebViewActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val apiKey = BuildConfig.NEWS_API_KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (apiKey.isEmpty()) {
            Toast.makeText(this, "API Anahtarı bulunamadı! Lütfen local.properties dosyasını kontrol edin.", Toast.LENGTH_LONG).show()
            Log.e("API_KEY_ERROR", "API Key is missing in BuildConfig.")

            binding.progressBar.visibility = View.GONE
            return
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.progressBar.visibility = View.VISIBLE

        RetrofitInstance.api.getTopHeadlines(country = "us", apiKey = apiKey)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    binding.progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        val articles = response.body()?.articles
                        if (articles != null && articles.isNotEmpty()) {
                            binding.recyclerView.adapter = NewsAdapter(articles) { article ->
                                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                                intent.putExtra("url", article.url)
                                startActivity(intent)
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "Haber bulunamadı veya API'den boş yanıt geldi.", Toast.LENGTH_LONG).show()
                            Log.d("API_RESPONSE", "Response successful but articles are null or empty. Body: ${response.body()}")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(this@MainActivity, "Veri alınamadı! Hata kodu: ${response.code()}", Toast.LENGTH_LONG).show()
                        Log.e("API_ERROR", "Response Code: ${response.code()}, Message: ${response.message()}, Error Body: $errorBody")
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, "Ağ hatası: ${t.message}", Toast.LENGTH_LONG).show()
                    Log.e("API_FAILURE", "Error: ${t.message}", t)
                }
            })
    }
}