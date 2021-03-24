/*
 * Copyright 2021 SOUP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package soup.compose.material.motion.sample.ui.demo

import androidx.annotation.DrawableRes
import soup.compose.material.motion.sample.R

/** A static store of data to be used in the transition's main music player flow example.  */
object MusicData {

    val albums: List<Album> = listOf(
        Album(
            0L,
            "Metamorphosis",
            "Sandra Adams",
            R.drawable.album_efe_kurnaz_unsplash,
            "52 mins"
        ),
        Album(
            1L,
            "Continuity",
            "Ali Connors",
            R.drawable.album_pawel_czerwinski_unsplash,
            "92 mins"
        ),
        Album(
            2L,
            "Break Point",
            "David Park",
            R.drawable.album_jean_philippe_delberghe_unsplash,
            "45 mins"
        ),
        Album(
            3L,
            "Headspace",
            "Charlie z.",
            R.drawable.album_karina_vorozheeva_unsplash,
            "65 mins"
        ),
        Album(
            4L,
            "New Neighbors",
            "Trevor Hansen",
            R.drawable.album_amy_shamblen_unsplash,
            "73 mins"
        ),
        Album(
            5L,
            "Spaced Out",
            "Jonas Eckhart",
            R.drawable.album_pawel_czerwinski_unsplash_2,
            "4 mins"
        ),
        Album(
            6L,
            "Holding on",
            "Elizabeth Park",
            R.drawable.album_kristopher_roller_unsplash,
            "40 mins"
        ),
        Album(
            7L,
            "Persistence",
            "Britta Holt",
            R.drawable.album_emile_seguin_unsplash,
            "39 mins"
        ),
        Album(
            8L,
            "At the Top",
            "Annie Chiu",
            R.drawable.album_ellen_qin_unsplash,
            "46 mins"
        ),
        Album(
            9L,
            "On Dry Land",
            "Alfonso Gonzalez",
            R.drawable.album_david_clode_unsplash,
            "55 mins"
        )
    )

    fun getAlbumById(albumId: Long): Album? {
        return albums.find { it.id == albumId }
    }

    /** A data class to hold information about a music album.  */
    data class Album(
        val id: Long,
        val title: String,
        val artist: String,
        @DrawableRes val cover: Int,
        val duration: String,
    )
}
