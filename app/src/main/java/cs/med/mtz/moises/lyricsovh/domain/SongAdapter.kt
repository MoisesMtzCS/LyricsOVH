package cs.med.mtz.moises.lyricsovh.domain

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cs.med.mtz.moises.lyricsovh.R
import cs.med.mtz.moises.lyricsovh.domain.entity.ActivityLyricSong
import cs.med.mtz.moises.lyricsovh.domain.entity.Song

class SongAdapter(
    private val songs: List<Song>
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    /* */
    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /* */
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivCover: ImageView = itemView.findViewById(R.id.iv_cover)

    }

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_song, parent, false)
        return SongViewHolder(view)
    }

    /**
     *
     */
    override fun getItemCount(): Int = songs.size


    /**
     *
     */
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: Song = songs[position]
        holder.tvTitle.text = song.title
        Glide.with(holder.itemView)
            .load(song.imageUrl)
            .into(holder.ivCover)

    }


    /**
     *+
     */


}