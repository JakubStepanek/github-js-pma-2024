@file:Suppress("unused", "UNUSED_VARIABLE", "PreviewMustBeTopLevelFunction")

package com.example.myapp009adatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp009adatastore.ui.theme.MyApp009aDataStoreTheme
//důležitý import!!!
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import java.util.prefs.Preferences


class MainActivity : ComponentActivity() {

    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp009aDataStoreTheme {
                UserForm()
            }
        }
    }
}


@Composable
fun UserForm() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        var username by remember { mutableStateOf("") }
        var userAge by remember { mutableStateOf("") }
        val numberPattern = remember { Regex("^\\d+\$") }
        var isChecked by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = username,
            onValueChange = { newText -> username = newText },
            label = { Text("Jméno") }
        )

        OutlinedTextField(
            value = userAge,
            label = { Text("Věk") },
            onValueChange = {
                if (it.isEmpty() || it.matches(numberPattern)) {
                    userAge = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text(
                "Potvrzuji, že jsem starší 18 let."
            )
        }
        Row() {
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                onClick = { saveData(username, userAge.toInt(), isChecked) }) {
                Text("Uložit")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("Načíst")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    MyApp009aDataStoreTheme {
        UserForm()
    }
}

fun saveData(username: String, age: Int, checked: Boolean) {
    //todo: save in DataStore
}
