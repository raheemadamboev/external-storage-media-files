package xyz.teamgravity.externalstoragemediafiles

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.ui.graphics.vector.ImageVector

enum class MediaType(
    val mimeTypePrefix: String,
    val icon: ImageVector?
) {
    Image(
        mimeTypePrefix = "image/",
        icon = null
    ),
    Video(
        mimeTypePrefix = "video/",
        icon = Icons.Default.Videocam
    ),
    Audio(
        mimeTypePrefix = "audio/",
        icon = Icons.Default.Audiotrack
    );

    companion object {
        fun fromMimeType(value: String): MediaType? = entries.firstOrNull { value.startsWith(it.mimeTypePrefix) }
    }
}