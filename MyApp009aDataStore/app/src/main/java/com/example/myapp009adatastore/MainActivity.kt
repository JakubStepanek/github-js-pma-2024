@file:Suppress("unused", "UNUSED_VARIABLE", "PreviewMustBeTopLevelFunction")

package com.example.myapp009adatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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


class MainActivity : ComponentActivity() {
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
        var text by remember { mutableStateOf("") }


        OutlinedTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text("Jméno") }
        )


        val pattern = remember { Regex("^\\d+\$") }
        var age by remember { mutableStateOf("") }

        OutlinedTextField(
            value = age,
            label = { Text("Věk") },
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    age = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        //todo: Check box

        var checked by remember { mutableStateOf(true) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Checkbox(
                checked = !checked,
                onCheckedChange = { checked = it }
            )
            Text(
                "Potvrzení, že jsem starší 18 let."
            )
        }
        Row() {
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp),
                onClick = { saveData() }) {
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

fun saveData(){

}
