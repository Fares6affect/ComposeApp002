package com.example.composeapp002

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp002.ui.theme.ComposeApp002Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApp002Theme {
                val langs = listOf("Kotlin", "Java", "JavaScript", "Rust", "C++", "Python")
                val settinglangs = remember { mutableStateListOf<String>() }
                Column(
                    modifier = Modifier.systemBarsPadding()
                ) {
                    langs.forEach { lang ->
                        val isCheck = lang in settinglangs
                        val toggle = {
                            if(isCheck)
                                settinglangs.remove(lang)
                            else
                                settinglangs.add(lang)
                            Unit
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color.Green)
                                .fillMaxWidth()
                                .clickable(onClick = toggle)
                        ){
                            Checkbox(
                                checked = isCheck,
                                onCheckedChange = { toggle() },
                                colors = CheckboxDefaults.colors(Color.Gray)
                            )
                            Text(lang,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(Color.Gray))
                        }
                    }
                    Text(text = "Выбранные элементы: " + settinglangs.joinToString(" "))
                    val (selected, onSelected) = remember { mutableStateOf(langs[0]) }
                    Column(
                        modifier = Modifier.selectableGroup()
                    ) {
                        langs.forEach { lang ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(Color.Green)
                                    .fillMaxWidth()
                                    .clickable(onClick = { onSelected(lang) })
                            ){
                                RadioButton(
                                    selected = (lang == selected),
                                    onClick = { onSelected(lang) }
                                )
                                Text(lang,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .background(Color.Gray))
                            }
                        }
                    }
                    Text(text = "Выбранный элемент: " + selected)
                }
            }
        }
    }
}