package com.ribonsipayung.foodrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.ribonsipayung.foodrecipes.data.FoodRecipes.recipeList
import com.ribonsipayung.foodrecipes.model.Recipe
import com.ribonsipayung.foodrecipes.ui.theme.FoodRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipesTheme {
                // Aplikasi resep makanan
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Membuat NavController untuk mengatur navigasi
                    val navController = rememberNavController()

                    // Navigasi menggunakan NavHost
                    NavHost(
                        navController = navController,
                        startDestination = "recipeList"
                    ) {

                        // Halaman daftar resep makanan
                        composable("recipeList") {
                            RecipeApp(navController)
                        }
                        // Halaman detail resep makanan
                        composable("recipe/{recipeName}") { backStackEntry ->
                            val recipeName = backStackEntry.arguments?.getString("recipeName")
                            // Mencari resep berdasarkan nama
                            val recipe = recipeList.find { it: Recipe -> it.name == recipeName }
                            if (recipe != null) {
                                // Menampilkan halaman detail resep
                                RecipeDetail(recipe, onBack = { navController.popBackStack() })
                            }
                        }
                    }
                }
            }
        }
    }
}

// Fungsi di bawah untuk menampilkan halaman utama aplikasi resep makanan
// Fungsi ini menerima [navController] untuk mengatur navigasi antar halaman
@Composable
fun RecipeApp(navController: NavHostController) {
    // Mutable state untuk teks pencarian
    var searchText by remember { mutableStateOf("") }
    // Menggunakan `derivedStateOf` untuk menghitung daftar resep berdasarkan hasil pencarian
    val recipes by remember { derivedStateOf { filterRecipes(recipeList, searchText) } }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Judul aplikasi
        Text(
            text = "Resep Makanan",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )

        // Kolom/kotak pencarian resep
        var isPlaceholderVisible by remember { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Ikon pencarian
                Icon(
                    painter = painterResource(id = R.drawable.icon_search),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Kotak pencarian resep
                BasicTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        isPlaceholderVisible = it.isEmpty() // Mengubah isPlaceholderVisible berdasarkan apakah teks kosong atau tidak
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            // Handle search action jika diperlukan
                        }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(0.dp)
                )

            }

            // Placeholder pencarian
            if (isPlaceholderVisible) {
                Text(
                    text = "Cari resep makanan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 32.dp)
                )
            }
        }

        // Daftar resep makanan
        LazyColumn {
            items(recipes) { recipe ->
                RecipeItem(recipe) {
                    // Navigasi ke halaman detail resep saat item diklik
                    navController.navigate("recipe/${recipe.name}")
                }
            }
        }
    }
}

// Fungsi di bawah berfungsi untuk menampilkan elemen kartu pada daftar resep makanan
// Fungsi ini menerima objek [recipe] dan ekspresi lambda [onItemClick] sebagai parameter
@Composable
fun RecipeItem(recipe: Recipe, onItemClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Gambar resep
                Image(
                    painter = painterResource(id = recipe.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp).clip(MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    //Judul resep
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    // Menampilkan deskripsi resep makanan di bawah judul
                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                        modifier = Modifier.clickable { isExpanded = !isExpanded }
                    )
                }
            }
        }
    }
}

// Fungsi di bawah untuk menampilkan halaman detail resep makanan
// Fungsi ini menerima objek [recipe] yang akan ditampilkan dan ekspresi lambda [onBack] sebagai parameter
@Composable
fun RecipeDetail(recipe: Recipe, onBack: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                // Judul resep
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )

                // Gambar resep
                Image(
                    painter = painterResource(id = recipe.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                // Deskripsi resep
                Text(
                    text = "Deskripsi:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )

                // Bahan-bahan
                Text(
                    text = "Bahan-bahan:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                for (ingredient in recipe.ingredients) {
                    Text(
                        text = "- $ingredient",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 32.dp)
                    )
                }

                // Instruksi
                Text(
                    text = "Cara Membuat:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
                for ((index, instruction) in recipe.instructions.withIndex()) {
                    // Menampilkan langkah-langkah pembuatan resep
                    Text(
                        text = "${index + 1}. $instruction",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 32.dp)
                    )
                }
            }

            item {
                // Tombol Kembali
                Button(
                    onClick = { onBack() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Kembali")
                }
            }
        }
    }
}

// Preview aplikasi
@Preview(showBackground = true)
@Composable
fun RecipeAppPreview() {
    FoodRecipesTheme {
        // Menampilkan tampilan daftar resep makanan sebagai contoh
        RecipeApp(navController = rememberNavController())
    }
}

// Fungsi untuk melakukan filter resep berdasarkan kata kunci pencarian
private fun filterRecipes(recipes: List<Recipe>, query: String): List<Recipe> {
    return recipes.filter { it.name.contains(query, ignoreCase = true) }
}