package com.mesttra.galeria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mesttra.galeria.R
import com.mesttra.galeria.activities.MainActivity
import com.mesttra.galeria.models.Photo
import com.squareup.picasso.Picasso

class PhotosAdapter(private val photos: List<Photo>, private val context: Context) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<LinearLayout>(R.id.photo_card_view)
        val titulo = itemView.findViewById<TextView>(R.id.photo_title)
        val image = itemView.findViewById<ImageView>(R.id.photo_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo: Photo = photos[position]

        holder.titulo.text = photo.title

        Picasso.get().load(photo.thumbnailUrl).into(holder.image);

//        holder.cardView.setOnClickListener { (context as MainActivity).mostrarDetalhes(position) }
    }
}