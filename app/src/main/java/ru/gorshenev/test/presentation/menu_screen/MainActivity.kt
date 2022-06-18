package ru.gorshenev.test.presentation.menu_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import ru.gorshenev.test.R
import ru.gorshenev.test.presentation.list_screen.ListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.buttonCharacters).setOnClickListener {
            onNextScreenClick(ScreenType.Characters(R.string.characters))
        }

        findViewById<Button>(R.id.buttonLocations).setOnClickListener {
            onNextScreenClick(ScreenType.Locations(R.string.locations))
        }

        findViewById<Button>(R.id.buttonEpisodes).setOnClickListener {
            onNextScreenClick(ScreenType.Episodes(R.string.episodes))
        }
    }

    private fun onNextScreenClick(screenType: ScreenType) {
        val nextScreen = Intent(this, ListActivity::class.java).apply {
            putExtra(
                EXTRA_SCREEN_TYPE,
                bundleOf(BUNDLE_SCREEN_TYPE to screenType)
            )
        }
        startActivity(nextScreen)
    }

    companion object {
        const val EXTRA_SCREEN_TYPE = "EXTRA_SCREEN_TYPE"
        const val BUNDLE_SCREEN_TYPE = "BUNDLE_SCREEN_TYPE"
    }

}