package com.example.newsapplication.adapter
import com.example.newsapplication.model.Article
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter(private val articles: List<Article>, private val onClick: (Article) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.tvTitle.text = article.title
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.binding.ivThumbnail)
        holder.itemView.setOnClickListener { onClick(article) }
    }

    override fun getItemCount() = articles.size
}