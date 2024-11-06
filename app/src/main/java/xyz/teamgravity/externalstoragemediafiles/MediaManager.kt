package xyz.teamgravity.externalstoragemediafiles

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.ContextCompat
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaManager(
    private val context: Context
) {

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    suspend fun getAll(): ImmutableList<MediaModel> {
        return withContext(Dispatchers.IO) {
            if (
                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) return@withContext persistentListOf()

            val data = mutableListOf<MediaModel>()
            val uri = MediaStore.Files.getContentUri(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) MediaStore.VOLUME_EXTERNAL else "external")

            context.contentResolver.query(
                uri,
                arrayOf(
                    MediaStore.Files.FileColumns._ID,
                    MediaStore.Files.FileColumns.DISPLAY_NAME,
                    MediaStore.Files.FileColumns.MIME_TYPE
                ),
                null,
                null,
                null
            )?.use { cursor ->
                val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                val displayNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val mimeTypeIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idIndex)
                    val displayName = cursor.getString(displayNameIndex) ?: continue
                    val mimeType = cursor.getString(mimeTypeIndex) ?: continue
                    val type = MediaType.fromMimeType(mimeType) ?: continue

                    data.add(
                        MediaModel(
                            id = id,
                            uri = ContentUris.withAppendedId(uri, id),
                            name = displayName,
                            type = type
                        )
                    )
                }
            }

            return@withContext data.toImmutableList()
        }
    }
}