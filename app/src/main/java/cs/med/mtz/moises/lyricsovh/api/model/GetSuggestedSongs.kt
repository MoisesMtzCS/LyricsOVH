package cs.med.mtz.moises.lyricsovh.api.model

import cs.med.mtz.moises.lyricsovh.api.model.model.dto.dto.SongDTO
import cs.med.mtz.moises.lyricsovh.domain.entity.Song

data class GetSuggestedSongs(
    val data : List<SongDTO>
)