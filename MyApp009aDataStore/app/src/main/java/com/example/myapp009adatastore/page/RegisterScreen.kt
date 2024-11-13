package com.example.myapp009adatastore.page

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.myapp009adatastore.R
import com.example.myapp009adatastore.model.UserDetails
import com.example.myapp009adatastore.ui.theme.MyApp009aDataStoreTheme
import com.example.myapp009adatastore.utils.DataStoreManager
import com.example.myapp009adatastore.utils.DataStoreManager.Companion.NAME
import com.example.myapp009adatastore.utils.preferenceDataStore
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class RegisterScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp009aDataStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val dataStoreContext = LocalContext.current
                    val dataStoreManager = DataStoreManager(dataStoreContext)

                    AppContent(
                        this@RegisterScreen,
                        preferenceDataStore,
                        dataStoreManager
                    )

                }
            }
        }
    }
}

@Composable
fun AppContent(
    registerScreen: RegisterScreen,
    preferenceDataStore: DataStore<Preferences>,
    dataStoreManager: DataStoreManager
) {

    var isRegistered by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val onRegisterSuccess = { isRegistered = true }
    val onLogout = {
        isRegistered = false
        scope.launch {
            dataStoreManager.clearDataStore()
        }
    }

    //lets check if user is registered in when the app start
    LaunchedEffect(key1 = Unit) {
        checkRegisterState(preferenceDataStore) { it ->
            isRegistered = it
        }
    }

    if (isRegistered) {
        HomePage(onLogout, dataStoreManager)
    } else {
        RegisterPageUI(onRegisterSuccess, dataStoreManager)
    }

}

suspend fun checkRegisterState(
    preferenceDataStore: DataStore<Preferences>,
    onResult: (Boolean) -> Unit
) {
    val preferences = preferenceDataStore.data.first()
    val name = preferences[NAME]
    val isRegistered = name != null
    onResult(isRegistered)
}


@Composable
fun RegisterPageUI(onRegisterSuccess: () -> Unit, dataStoreManager: DataStoreManager) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var agreement by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    val scope = rememberCoroutineScope()

    // Create focus requesters for each TextField
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }

    // Get the current focus manager
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Add verticalScroll modifier here
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 8.dp)
            ) {

                // Heading Jetpack Compose
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp

                )

                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    text = "Register",
                    fontSize = 25.sp
                )

                // Name Field
                OutlinedTextField(
                    value = name, // Add a variable for name
                    onValueChange = { name = it },
                    label = { Text("Jméno") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next // Set imeAction to Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester1.requestFocus() // Move to next TextField
                        }
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester1)
                )

                OutlinedTextField(
                    value = age, // Add a variable for name
                    onValueChange = { newValue ->
                        // Povolit pouze číselné znaky
                        if (newValue.all { it.isDigit() }) {
                            age = newValue
                        }
                    },
                    label = { Text("Věk") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next // Set imeAction to Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.clearFocus() //hide keyboard
                        }
                    ),

                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester1)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Checkbox(
                        checked = agreement,
                        onCheckedChange = { agreement = it }
                    )
                    Text(
                        "Potvrzuji, že jsem starší 18 let."
                    )
                }

                Button(
                    onClick = {
                        if (name.isEmpty()) {
                            Toast.makeText(mContext, "Jméno nesmí být prázdné", Toast.LENGTH_SHORT)
                                .show()
                        } else if (age.toInt() < 18 && age.isEmpty()) {
                            Toast.makeText(mContext, "Věk nesmí být prázdný", Toast.LENGTH_SHORT)
                                .show()
                        } else if (age.toInt() < 18 && agreement) {
                            Toast.makeText(mContext, "Kecáš! Není Ti 18 let", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            //Submit you data
                            scope.launch {
                                dataStoreManager.saveToDataStore(
                                    UserDetails(
                                        name = name,
                                        age = age.toInt(),
                                        agreement = agreement
                                    )
                                )
                                onRegisterSuccess()
                            }
                        }

                    }, modifier = Modifier.padding(16.dp)
                ) {
                    Text("Submit")
                }

            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(onLogout: () -> Job, dataStoreManger: DataStoreManager) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
            title = { Text("HomePage", color = Color.White) },
        )

    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val userDetails by dataStoreManger.getFromDataStore().collectAsState(initial = null)
            Text(
                text = "Hi, ${"\nWelcome to the Home Page "}",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {

                Text(
                    text = "Name: ${userDetails?.name ?: ""}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Age: ${userDetails?.age ?: ""}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Uživatel souhlasil:: ${userDetails?.agreement ?: ""}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Logout Button
                Button(
                    onClick = { onLogout() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Logout",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }


        }
    }
}

