package com.ribonsipayung.foodrecipes.data

import com.ribonsipayung.foodrecipes.R
import com.ribonsipayung.foodrecipes.model.Recipe

object FoodRecipes {
    // Objek FoodRecipes menyimpan daftar resep makanan
    val recipeList = listOf(
        Recipe("Rendang", "Rendang adalah masakan daging tradisional dari Indonesia.", R.drawable.rendang,
            listOf("Daging sapi", "Santan", "Bumbu rendang", "Serai", "Daun jeruk", "Lengkuas"),
            listOf("Potong daging sapi menjadi potongan kecil.", "Rebus daging sapi dengan santan hingga empuk.", "Masukkan bumbu rendang, serai, daun jeruk, dan lengkuas.", "Masak hingga rendang matang dan berkuah.")),
        Recipe("Spaghetti Carbonara", "Spaghetti Carbonara adalah hidangan pasta klasik Italia.", R.drawable.spaghetti,
            listOf("Spaghetti", "Telur", "Keju Parmesan", "Daging bacon", "Bawang putih", "Peterseli"),
            listOf("Rebus spaghetti hingga matang.", "Campur telur, keju Parmesan, dan peterseli.", "Tumis daging bacon dan bawang putih hingga garing.", "Campur spaghetti dan aduk dengan campuran telur.")),
        Recipe("Kimbab", "Kimbab adalah makanan ringan Korea berupa gulungan nasi.", R.drawable.kimbab,
            listOf("Nasi", "Rumput laut", "Wortel", "Sayuran", "Telur", "Keju"),
            listOf("Bumbui nasi dengan garam dan wijen.", "Letakkan nasi di atas lembar rumput laut.", "Tambahkan wortel, sayuran, telur, dan keju.", "Gulung kimbab dan potong menjadi potongan kecil.")),
        Recipe("Opor", "Opor ayam adalah hidangan khas Indonesia berupa ayam dengan santan.", R.drawable.opor,
            listOf("Ayam", "Santan", "Bumbu opor", "Serai", "Daun salam", "Ketupat"),
            listOf("Potong ayam menjadi potongan kecil.", "Rebus ayam dengan santan hingga empuk.", "Masukkan bumbu opor, serai, dan daun salam.", "Sajikan dengan ketupat.")),
        Recipe("Bolu Pisang", "Bolu pisang adalah kue manis dengan rasa pisang yang lezat.", R.drawable.bolu_pisang,
            listOf("Pisang matang", "Tepung terigu", "Telur", "Gula", "Margarin", "Pewarna makanan"),
            listOf("Hancurkan pisang matang.", "Campurkan pisang dengan tepung terigu, telur, gula, dan margarin.", "Tambahkan pewarna makanan sesuai keinginan.", "Panggang hingga matang dan berwarna emas.")),
    )

}