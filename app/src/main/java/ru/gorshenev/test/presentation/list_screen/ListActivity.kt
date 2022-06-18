package ru.gorshenev.test.presentation.list_screen

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.gorshenev.test.R
import ru.gorshenev.test.data.MortyRepository
import ru.gorshenev.test.presentation.list_screen.adapter.ItemInfoAdapter
import ru.gorshenev.test.presentation.list_screen.model.ItemInfo
import ru.gorshenev.test.presentation.menu_screen.MainActivity
import ru.gorshenev.test.presentation.menu_screen.ScreenType

class ListActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText

    private lateinit var recyclerView: RecyclerView

    private val infoAdapter: ItemInfoAdapter = ItemInfoAdapter()

    private val repository: MortyRepository = MortyRepository()

    private var cachedItems: List<ItemInfo> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<ImageView>(R.id.ivCancelSearch).setOnClickListener { etSearch.text.clear() }

        etSearch = findViewById(R.id.etSearch)

        recyclerView = findViewById<RecyclerView>(R.id.rvItems).apply {
            adapter = infoAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        initContent()
        initSearch()
    }

    private fun initContent() {
        val screenTypeBundle = intent.getBundleExtra(MainActivity.EXTRA_SCREEN_TYPE)
        val screenTypeSerializable =
            screenTypeBundle?.let { bundle -> bundle.getSerializable(MainActivity.BUNDLE_SCREEN_TYPE) }
        val screenType = screenTypeSerializable as? ScreenType
        screenType?.let {
            setTitle(it.titleRes)
            loadData(it)
        }
    }

    private fun initSearch() {
        etSearch.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) {
                val filteredItems = cachedItems.filter { it.name.contains(text, true) }
                infoAdapter.updateItems(filteredItems)
            } else {
                infoAdapter.updateItems(cachedItems)
            }
        }
    }

    private fun loadData(screenType: ScreenType) {
        repository.getItems(
            screenType = screenType,
            page = 1,
            onSuccess = { items ->
                infoAdapter.updateItems(items)
                cachedItems = items
            },
            onError = { error ->
                Log.d("Morty", "${error.printStackTrace()}")
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }
        )
    }

}
