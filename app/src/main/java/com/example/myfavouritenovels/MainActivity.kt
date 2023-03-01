package com.example.myfavouritenovels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavouritenovels.data.ListNovelAdapter
import com.example.myfavouritenovels.data.NovelHeader
import com.example.myfavouritenovels.data.NovelInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.color.DynamicColors


class MainActivity : AppCompatActivity() {
    private lateinit var rvNovels: RecyclerView
    private val listNovel =  ArrayList<NovelHeader>()
    private lateinit var listNovelInfo: ArrayList<NovelInfo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val novelsAsset = assets.open("novels.json").bufferedReader().use { it.readText() }
        val listNovelInfoType = object : TypeToken<ArrayList<NovelInfo>>() {}.type
        val gson = Gson()
        listNovelInfo = gson.fromJson(novelsAsset, listNovelInfoType)

        listNovelInfo.forEach{
            listNovel.add(NovelHeader(it))
        }

        rvNovels = findViewById(R.id.rv_novels)
        rvNovels.setHasFixedSize(true)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvNovels.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListNovelAdapter(listNovel)
        rvNovels.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback {
            val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            detailIntent.putExtra(getString(R.string.NOVEL_EXTRA), listNovelInfo[it])
            startActivity(detailIntent)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}