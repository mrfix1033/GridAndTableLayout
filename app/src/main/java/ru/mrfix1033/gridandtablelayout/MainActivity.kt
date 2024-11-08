package ru.mrfix1033.gridandtablelayout

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var toolBar: Toolbar
    private lateinit var input: EditText
    private lateinit var result: TextView

    private var equalsClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // поиск элементов
        gridLayout = findViewById(R.id.gridLayout)
        input = findViewById(R.id.input)
        result = findViewById(R.id.result)
        toolBar = findViewById(R.id.toolBar)

        // настройка ToolBar
        title = "Калькулятор"
        setSupportActionBar(toolBar)
        toolBar.navigationIcon =
            AppCompatResources.getDrawable(this, R.drawable.baseline_calculate_24)
        toolBar.overflowIcon =
            AppCompatResources.getDrawable(this, R.drawable.baseline_accessible_forward_24)

        // настройка кнопок
        for (child in gridLayout.children) {
            if (child is Button)
                child.setOnClickListener(::buttonListener)
        }
    }

    private fun buttonListener(view: View) {
        val button = view as Button
        val text = button.text.toString()
        if (equalsClicked) {
            input.setText(result.text)
            equalsClicked = false
        }
        text.toIntOrNull()?.let {
            input.text.append(text)
        }
        when (text) {
            "+", "-", "*", "/" -> input.text.append(text)

            "=" -> result.setText(
                Utils.executeExpression(input.text.toString()).let {
                    if (it == null) "Ошибка"
                    else {
                        equalsClicked = true
                        it.toString()
                    }
                }
            )

            "reset" -> {
                input.text.clear()
                result.text = ""
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemExit -> finish()
        }
        return true
    }
}