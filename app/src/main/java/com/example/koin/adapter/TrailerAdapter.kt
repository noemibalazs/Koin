package com.example.koin.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.R
import com.example.koin.network_data.Trailer
import com.example.koin.util.getMovieYoutubePath
import com.example.koin.util.getYoutubeScreenShot
import com.example.koin.util.loadPicture

class TrailerAdapter(val context: Context, val trailers: List<Trailer>) :
    RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_trailer, parent, false)
        return TrailerViewHolder(view)
    }


    override fun getItemCount(): Int {
        return trailers.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val trailer = trailers[position]
        holder.name.text = trailer.name
        context.loadPicture(getYoutubeScreenShot(trailer.key), holder.picture)

        holder.picture.setOnClickListener {
            viewTrailer(getMovieYoutubePath(trailer.key))
        }
    }

    inner class TrailerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.trailer_name)
        val picture = view.findViewById<ImageView>(R.id.trailer_play)

    }

    private fun viewTrailer(link:String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            data = Uri.parse(link)
        }
        context.startActivity(intent)
    }
}