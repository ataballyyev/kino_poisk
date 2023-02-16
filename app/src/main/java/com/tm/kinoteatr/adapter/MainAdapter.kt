package com.tm.kinoteatr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tm.kinoteatr.R
import com.tm.kinoteatr.data.model.Film

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainVH>() {
    private var movies: ArrayList<Film> = arrayListOf()
    private lateinit var listener: OnClickListener

    inner class MainVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val imageFilm: ImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(film: Film) {
            Glide.with(itemView)
                .load(film.posterUrl)
                .into(imageFilm)
            title.text = film.nameRu
        }

        init {
            itemView.setOnClickListener {
                listener.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return MainVH(view)
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    fun initializeList(list: List<Film>) {
        movies = ArrayList(list)
        notifyDataSetChanged()
    }

    fun initializeListener(onClickListener: OnClickListener) {
        listener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}