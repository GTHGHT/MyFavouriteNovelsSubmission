package com.example.myfavouritenovels

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myfavouritenovels.data.NovelInfo
import com.example.myfavouritenovels.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var novel: NovelInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val novel = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(getString(R.string.NOVEL_EXTRA), NovelInfo::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(getString(R.string.NOVEL_EXTRA))
        }

        if (novel != null){
            this.novel = novel
            setInfo()
        }

        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    fun setInfo(): Unit {
        binding.detailToolbar.title = novel.title
        binding.tvTitle.text = novel.title
        binding.tvAuthors.text = novel.authors.reduce { oldValue, nextValue ->
            "$oldValue, ${nextValue.trim()}"
        }
        binding.tvLanguage.text = novel.language
        binding.tvType.text = novel.type.trim()
        binding.tvYear.text = novel.year.trim()
        val rating = novel.rating.toDouble().toInt()
        binding.tvRating.text = String.format(
            getString(R.string.rating_placeholder),
            "✦".repeat(rating),
            "✧".repeat(5 - rating),
            novel.rating
        )
        binding.tvGenre.text = novel.genre.reduce { oldValue, nextValue ->
            "$oldValue, $nextValue"
        }
        binding.tvTag.text = novel.tag.reduce { oldValue, nextValue ->
            "$oldValue, $nextValue"

        }
        binding.tvDescription.text = novel.description
        Glide.with(binding.ivCover)
            .load(novel.img_link)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(binding.ivCover)

        binding.buttonNovel.setOnClickListener {
            val viewNovel = Intent(Intent.ACTION_VIEW)
            viewNovel.setData(Uri.parse(novel.sid))
            startActivity(viewNovel)
        }

        binding.buttonImage.setOnClickListener {
            val viewImage = Intent(Intent.ACTION_VIEW)
            viewImage.setData(Uri.parse(novel.img_link))
            startActivity(viewImage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("action_bar",  "Action Bar Clicked")
        return when (item.itemId) {
            R.id.action_share->{
                Log.d("action_bar",  "Action Bar Clicked")
                val shareNovel = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        String.format(getString(R.string.share_placeholder), novel.title, novel.sid)
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(shareNovel, "Send Novels")
                startActivity(shareIntent)
                true
            }
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> {
                Log.d("action_bar",  "Action Bar Missed")
                true
            }
        }
    }

}