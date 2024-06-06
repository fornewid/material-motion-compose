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
package soup.compose.material.motion.shared.demo

import material_motion_compose.sample.shared.generated.resources.Res
import material_motion_compose.sample.shared.generated.resources.album_amy_shamblen_unsplash
import material_motion_compose.sample.shared.generated.resources.album_david_clode_unsplash
import material_motion_compose.sample.shared.generated.resources.album_efe_kurnaz_unsplash
import material_motion_compose.sample.shared.generated.resources.album_ellen_qin_unsplash
import material_motion_compose.sample.shared.generated.resources.album_emile_seguin_unsplash
import material_motion_compose.sample.shared.generated.resources.album_jean_philippe_delberghe_unsplash
import material_motion_compose.sample.shared.generated.resources.album_karina_vorozheeva_unsplash
import material_motion_compose.sample.shared.generated.resources.album_kristopher_roller_unsplash
import material_motion_compose.sample.shared.generated.resources.album_pawel_czerwinski_unsplash
import material_motion_compose.sample.shared.generated.resources.album_pawel_czerwinski_unsplash_2
import org.jetbrains.compose.resources.DrawableResource

/** A static store of data to be used in the transition's main music player flow example.  */
object MusicData {

    private val tracks: List<Track> = listOf(
        Track(1, "First", "3:25", true),
        Track(2, "Second", "4:51", false),
        Track(3, "Third", "4:12", false),
        Track(4, "Fourth", "2:34", false),
        Track(5, "Fifth", "439", false),
        Track(6, "Sixth", "2:31", false),
        Track(7, "Seventh", "5:25", false),
        Track(8, "Eighth", "3:46", false),
        Track(9, "Ninth", "4:28", false),
        Track(10, "Tenth", "4:47", false),
        Track(11, "Eleventh", "5:14", false),
        Track(12, "Twelfth", "4:46", false),
        Track(13, "Thirteenth", "7:13", false),
        Track(14, "Fourteenth", "2:43", false),
    )

    val albums: List<Album> = listOf(
        Album(
            0L,
            "Metamorphosis",
            "Sandra Adams",
            Res.drawable.album_efe_kurnaz_unsplash,
            tracks,
            "52 mins",
        ),
        Album(
            1L,
            "Continuity",
            "Ali Connors",
            Res.drawable.album_pawel_czerwinski_unsplash,
            tracks,
            "92 mins",
        ),
        Album(
            2L,
            "Break Point",
            "David Park",
            Res.drawable.album_jean_philippe_delberghe_unsplash,
            tracks,
            "45 mins",
        ),
        Album(
            3L,
            "Headspace",
            "Charlie z.",
            Res.drawable.album_karina_vorozheeva_unsplash,
            tracks,
            "65 mins",
        ),
        Album(
            4L,
            "New Neighbors",
            "Trevor Hansen",
            Res.drawable.album_amy_shamblen_unsplash,
            tracks,
            "73 mins",
        ),
        Album(
            5L,
            "Spaced Out",
            "Jonas Eckhart",
            Res.drawable.album_pawel_czerwinski_unsplash_2,
            tracks,
            "4 mins",
        ),
        Album(
            6L,
            "Holding on",
            "Elizabeth Park",
            Res.drawable.album_kristopher_roller_unsplash,
            tracks,
            "40 mins",
        ),
        Album(
            7L,
            "Persistence",
            "Britta Holt",
            Res.drawable.album_emile_seguin_unsplash,
            tracks,
            "39 mins",
        ),
        Album(
            8L,
            "At the Top",
            "Annie Chiu",
            Res.drawable.album_ellen_qin_unsplash,
            tracks,
            "46 mins",
        ),
        Album(
            9L,
            "On Dry Land",
            "Alfonso Gonzalez",
            Res.drawable.album_david_clode_unsplash,
            tracks,
            "55 mins",
        ),
    )

    fun getAlbumById(albumId: Long): Album? {
        return albums.find { it.id == albumId }
    }

    /** A data class to hold information about a music album.  */
    data class Album(
        val id: Long,
        val title: String,
        val artist: String,
        val cover: DrawableResource,
        val tracks: List<Track>,
        val duration: String,
    )

    /** A data class to hold information about a track on an album.  */
    data class Track(
        val trackNo: Int,
        val title: String,
        val duration: String,
        var playing: Boolean,
    )
}
