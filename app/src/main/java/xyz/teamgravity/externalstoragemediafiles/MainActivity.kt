package xyz.teamgravity.externalstoragemediafiles

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.teamgravity.externalstoragemediafiles.ui.theme.ExternalStorageMediaFilesTheme

class MainActivity : ComponentActivity() {

    private val manager: MediaManager by lazy { MediaManager(applicationContext) }

    private val viewmodel by viewModels<MainViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return MainViewModel(manager) as T
                }
            }
        }
    )

    override fun onResume() {
        super.onResume()
        viewmodel.onRefresh()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestPermissions(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.READ_MEDIA_AUDIO
                )
            else
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            0
        )

        setContent {
            ExternalStorageMediaFilesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(
                            items = viewmodel.files,
                            key = { it.id }
                        ) { media ->
                            CardMedia(
                                media = media
                            )
                        }
                    }
                }
            }
        }
    }
}