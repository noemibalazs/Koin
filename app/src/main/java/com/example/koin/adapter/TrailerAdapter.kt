package com.example.koin.adapter

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.R
import com.example.koin.network_data.Trailer
import com.example.koin.util.getMoviePoster
import com.example.koin.util.getMovieYoutubePath
import com.example.koin.util.getYoutubeScreenShot
import com.example.koin.util.loadPicture

class TrailerAdapter(val context: Context, val trailers: MutableList<Trailer>) :
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

        val link = getYoutubeScreenShot(trailer.key)
        context.loadPicture(link, holder.picture)

        holder.picture.setOnClickListener {
            checkConnectionAndPlay(getMovieYoutubePath(trailer.key))
        }
    }

    inner class TrailerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.trailer_name)
        val picture = view.findViewById<ImageView>(R.id.trailer_play)

    }

    private fun checkConnectionAndPlay(link: String){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivityManager.activeNetworkInfo
        if (info !=null && info.isConnected){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.apply {
                data = Uri.parse(link)
            }
            context.startActivity(intent)
        }else{
            Toast.makeText(context, context.getString(R.string.toast_message), Toast.LENGTH_LONG).show()
        }

    }
}