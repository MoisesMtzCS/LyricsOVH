package cs.med.mtz.moises.lyricsovh.domain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cs.med.mtz.moises.lyricsovh.R
import cs.med.mtz.moises.lyricsovh.domain.entity.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /* */
    private val value: String
        get() = value_Text.text.toString()

    /* */
    private val songs: ArrayList<Song> = ArrayList()

    /* */
    private lateinit var mainViewModel: MainViewModel

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        setupViews()
        observeViewModel()
        searchOnClick()
    }

    /**
     *
     */
    private fun setupViews() {
        setupSongsRecyclerView()
    }

    /**
     *
     */
    private fun setupSongsRecyclerView() {
        val songAdapter = SongAdapter(songs)
        rv_Songs.adapter = songAdapter
        rv_Songs.layoutManager = LinearLayoutManager(this)
    }

    /**
     *
     */

    private fun observeViewModel() {
        mainViewModel.songsLiveData.observe(this, Observer<List<Song>> {
            songs.clear()
            songs.addAll(it)
            rv_Songs.adapter?.notifyDataSetChanged()
        })
    }
    /**
     *
     */

    private fun searchOnClick() {
        search_Button.setOnClickListener {
            if (value.isNotBlank()) {
                mainViewModel.executeLoadSongs(value)

            }
        }
    }
}