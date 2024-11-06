package xyz.teamgravity.externalstoragemediafiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CardMedia(
    media: MediaModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        when (media.type) {
            MediaType.Image -> {
                AsyncImage(
                    model = media.uri,
                    contentDescription = null,
                    modifier = Modifier.width(100.dp)
                )
            }

            MediaType.Video, MediaType.Audio -> {
                Image(
                    imageVector = media.type.icon!!,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier.width(16.dp)
        )
        Text(
            text = media.displayName,
            modifier = Modifier.fillMaxWidth()
        )
    }
}