package cs.med.mtz.moises.lyricsovh.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cs.med.mtz.moises.lyricsovh.api.model.ApiService
import cs.med.mtz.moises.lyricsovh.api.model.model.dto.dto.SongDTO
import cs.med.mtz.moises.lyricsovh.domain.entity.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    /* */
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.lyrics.ovh/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /* */
    private val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)


    /* */
    private val lyricMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val lyricLiveData: MutableLiveData<String> get() = lyricMutableLiveData


    /* */
    private val songsMutableLiveData: MutableLiveData<List<Song>> = MutableLiveData()
    val songsLiveData: LiveData<List<Song>> get() = songsMutableLiveData


    /**
     *
     */
    fun executeLoadSongs(valueSong: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = service.getSuggestSongs(valueSong)
                val songsDto: List<SongDTO> = response.data
                val songs: List<Song> = songsDto.map { it.toSong() }
                Log.e("MAIN/RES", songs.toString())
                songsMutableLiveData.postValue(songs)
            } catch (exception: Exception) {
                Log.e("MAIN/ERR", exception.message.toString())
            }


        }
    }

    /**
     *
     */

    fun executeLyricsSongs(artist: String, songName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getLyricSong(artist, songName)
                val lyrics: String = response.lyrics
            } catch (exception: Exception) {
                Log.e("MAIN/ERR", exception.message.toString())
            }
        }
    }
}