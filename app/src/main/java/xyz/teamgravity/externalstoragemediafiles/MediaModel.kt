package xyz.teamgravity.externalstoragemediafiles

import android.net.Uri

data class MediaModel(
    val id: Long,
    val uri: Uri,
    val name: String,
    val type: MediaType
) {
    val displayName: String get() = "$name - $type"
}