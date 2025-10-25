package com.example.practica4recycler1package

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica4recycler1.Article
import com.example.practica4recycler1.R
// Adaptador para el RecyclerView que muestra una lista de artículos
class ArticleAdapter(private val articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    // ViewHolder que representa cada ítem de artículo en la lista
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.articleimg)
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.artdesc)
    }
    // Infla el layout del ítem y crea un ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }
    // Vincula los datos del artículo al ViewHolder
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.img.setImageURI(Uri.parse(article.imageUri))
        holder.title.text = article.title
        holder.desc.text = article.description
    }
    // Retorna el número total de artículos
    override fun getItemCount() = articles.size
}