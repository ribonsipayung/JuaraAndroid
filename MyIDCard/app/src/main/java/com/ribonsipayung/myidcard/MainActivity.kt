package com.ribonsipayung.myidcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyIdCard(Message("Ribon Sipayung", "Software Developer"))
        }
    }
}


data class Message(val author: String, val body: String)

@Composable
fun MyIdCard(msg: Message) {
    // komponen Box digunakan untuk mengatur tata letak elemen-elemen di dalamnya.
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // menampilkan teks "MY PROFILE" di pusat atas dengan gaya huruf khusus.
        Text(
            text = "MY PROFILE",
            style = TextStyle(fontSize = 40.sp),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(50.dp)
        )

        // komponen Column digunakan untuk menata elemen-elemen vertikal.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //menampilkan gambar profile
            Image(
                painter = painterResource(R.drawable.myphoto),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // menampilkan nama pengguna
            Text(
                text = msg.author,
                style = TextStyle(fontSize = 35.sp)
            )

            // Menampilkan informasi pekerjaan
            Text(
                text = msg.body,
                style = TextStyle(fontSize = 18.sp)
            )
        }

        // menampilkan ikon-ikon kontak dalam kolom.
        val myIcons = listOf(
            Icon(R.drawable.email, "ribonsipayung02@gmail.com"),
            Icon(R.drawable.telepon, "087896151354"),
            Icon(R.drawable.home, "Yogyakarta, Indonesia")
        )

        // kolom untuk ikon-ikon kontak di bagian bawah.
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            for (contact in myIcons) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(contact.iconResId),
                        contentDescription = contact.iconContentDescription,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = contact.text,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
// data class untuk menyimpan informasi ikon kontak.
data class Icon(val iconResId: Int, val text: String, val iconContentDescription: String = "")


@Preview
@Composable
fun PreviewMyIdCard() {
    MyIdCard(
        msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
    )
}

