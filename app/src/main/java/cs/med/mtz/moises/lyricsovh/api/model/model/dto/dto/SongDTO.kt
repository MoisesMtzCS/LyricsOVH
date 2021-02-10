package cs.med.mtz.moises.lyricsovh.api.model.model.dto.dto

import cs.med.mtz.moises.lyricsovh.domain.entity.Song

data class SongDTO(
    val title: String,
    val album: AlbumDTO,
    val artist: ArtistDTO
){
    fun toSong(): Song =
        Song(
            title = title,
            imageUrl = album.cover_medium,
            artist = artist.name

        )
}