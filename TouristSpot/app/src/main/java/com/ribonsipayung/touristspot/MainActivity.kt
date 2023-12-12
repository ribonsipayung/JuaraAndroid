package com.ribonsipayung.touristspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ribonsipayung.touristspot.ui.theme.TouristSpotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur tampilan utama aplikasi dengan menggunakan TouristSpotTheme
        setContent {
            TouristSpotTheme {
                // Membuat tampilan utama dengan latar belakang sesuai warna tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //// Menampilkan daftar tempat wisata menggunakan TouristSpotList
                    TouristSpotList()
                }
            }
        }
    }
}

@Composable
fun TouristSpotList() {
    // Daftar tempat-tempat wisata
    val touristSpots = listOf(
        TouristSpot("Candi Borobudur", R.drawable.borobudur, "Kabupaten Magelang, Jawa Tengah", description = "Candi Borobudur (Jawa: ꦕꦟ꧀ꦝꦶꦧꦫꦧꦸꦝꦸꦂ, translit. Candhi Båråbudhur) adalah sebuah candi Buddha yang terletak di Borobudur, Magelang, Jawa Tengah, Indonesia. Candi ini terletak kurang lebih 100 km di sebelah barat daya Semarang, 86 km di sebelah barat Surakarta, dan 40 km di sebelah barat laut Yogyakarta. Candi dengan banyak stupa ini didirikan oleh para penganut agama Buddha Mahayana sekitar tahun 800-an Masehi pada masa pemerintahan wangsa Syailendra. Borobudur adalah candi atau kuil Buddha terbesar di dunia, sekaligus salah satu monumen Buddha terbesar di dunia." ),
        TouristSpot("Pantai Seminyak", R.drawable.seminyak, "Bali", description = "Seminyak adalah kelurahan di kecamatan Kuta, Badung, Bali, Indonesia. Daerah ini terkenal pula sebagai tempat wisata pantai, lengkap dengan berbagai fasilitas seperti hotel, spa, restoran, bar, kafe dan toko. Jalan utama yang melintasi Seminyak adalah Jl. Raya Seminyak."),
        TouristSpot("Raja Ampat", R.drawable.raja_ampat, "Papua Barat", description = "Kepulauan Raja Ampat merupakan rangkaian empat gugusan pulau yang berdekatan dan berlokasi di barat bagian Kepala Burung (Vogelkoop) Pulau Papua. Secara administrasi, gugusan ini berada di bawah Kabupaten Raja Ampat, Provinsi Papua Barat. Kepulauan ini sekarang menjadi tujuan para penyelam yang tertarik akan keindahan pemandangan bawah lautnya. Empat gugusan pulau yang menjadi anggotanya dinamakan menurut empat pulau terbesarnya, yaitu Pulau Waigeo, Pulau Misool, Pulau Salawati, dan Pulau Batanta."),
    )

    // Menampilkan daftar wisata dalam LazyColumn
    LazyColumn {
        item {
            // Headline untuk daftar wisata
            Text(
                text = "Destinasi Wisata Populer di Indonesia",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary // Sesuaikan dengan warna yang kita inginkan
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        // Menampilkan item wisata menggunakan TouristSpotItem
        items(touristSpots) { spot ->
            TouristSpotItem(spot)
        }
    }
}

@Composable
fun TouristSpotItem(spot: TouristSpot) {
    // State untuk mengontrol apakah deskripsi lengkap atau singkat yang ditampilkan
    var showFullDescription by remember { mutableStateOf(false) }

    // State untuk mengontrol tampilan tombol "Lebih banyak" dan "Lebih sedikit"
    var showButton by remember { mutableStateOf(false) }


    // Card digunakan untuk mengelompokkan konten
    Card(
        modifier = Modifier
            .fillMaxWidth() // Mengisi lebar maksimum
            .padding(16.dp), // Menambahkan padding
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Set image tempat wisata
            Image(
                painter = painterResource(id = spot.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // Mengisi lebar maksimum
                    .height(200.dp) // Menentukan tinggi gambar
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Set nama tempat wisata dengan gaya font dan warna yang diinginkan
            Text(
                text = spot.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Set lokasi dengan gaya font dan warna yang diinginkan
            Text(
                text = spot.location,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Set deskripsi tempat wisata dengan pengkondisian
            Text(
                text = if (showFullDescription) spot.description else spot.description.split(" ").take(25).joinToString(" "),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
            )

            // Set tombol "Lebih banyak" atau "Lebih sedikit" untuk mengontrol tampilan deskripsi
            if (showButton) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showFullDescription = !showFullDescription
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = if (showFullDescription) "Lebih sedikit" else "Lebih banyak")
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showButton = true
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Lebih banyak")
                }
            }
        }
    }
}

// Data class untuk menyimpan informasi tempat wisata
data class TouristSpot(val name: String, val imageResId: Int, val location: String, val description: String)


@Preview(showBackground = true)
@Composable
fun TouristSpotListPreview() {
    TouristSpotTheme {
        TouristSpotList()
    }
}
