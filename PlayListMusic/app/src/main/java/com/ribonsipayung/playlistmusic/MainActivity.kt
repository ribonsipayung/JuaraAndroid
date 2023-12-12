package com.ribonsipayung.playlistmusic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ribonsipayung.playlistmusic.data.MusicPlaylist
import com.ribonsipayung.playlistmusic.ui.theme.PlayListMusicTheme

data class Song(val title: String, val artist: String, val album: String, val coverImage: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayListMusicTheme {
                // Membungkus seluruh aplikasi dalam tema PlayListMusicTheme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MusicPlaylist()
                }
            }
        }
    }

    @Composable
    fun MusicPlaylist() {
        var isGridView by remember { mutableStateOf(false) }
        Column {
            Text(
                text = "Liked Albums", // Menampilkan judul "Liked Albums"
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Mengatur jarak antara elemen-elemen horizontal agar paling kiri dan paling kanan
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "+Album ",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = if (isGridView) "Grid View" else "List View",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        isGridView = !isGridView
                    }
                )
            }

            if (isGridView) {
                // Tampilan dalam mode "Grid View"
                MusicGrid()
            } else {
                // Tampilan dalam mode "List View"
                MusicList()
            }

        }
    }

    @Composable
    fun MusicList() {
        // Daftar lagu dalam tampilan "List View"
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(MusicPlaylist.playList) { song ->
                MusicItem(song)
            }
        }
    }
    @Composable
    fun MusicGrid() {
        // Daftar lagu dalam tampilan "Grid View"
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(MusicPlaylist.playList) { song ->
                MusicItem(song)
            }
        }
    }
    @Composable
    fun MusicItem(song: Song) {
        // Tampilan item lagu
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tampilan image album di sisi kiri
            Image(
                painter = painterResource(id = song.coverImage),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            // Judul dan nama penyanyi di sisi kanan
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = song.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun MusicPreview(){
        // Pratinjau tampilan album tunggal
        PlayListMusicTheme{
            val playList = Song("Star Boy", "TheWeeknd", "Album 1", R.drawable.cover1)
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MusicItem(song = playList)
            }
        }
    }
}



