package com.example.myapp009adatastore.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapp009adatastore.model.UserDetails
import kotlinx.coroutines.flow.map

const val USER_DATASTORE = "user_data"

val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class DataStoreManager(val context: Context) {

    companion object {
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val AGE = intPreferencesKey("AGE")
        val IS_CHECKBOX_CHECKED = booleanPreferencesKey("IS_CHECKBOX_CHECKED")
    }

    suspend fun saveToDataStore(userDetails: UserDetails) {
        context.preferenceDataStore.edit {
            it[USER_NAME] = userDetails.name
            it[AGE] = userDetails.age
            it[IS_CHECKBOX_CHECKED] = userDetails.ageCheckboxChecked
        }
    }

    fun getFromDataStore() = context.preferenceDataStore.data.map {
        UserDetails(
            name = it[USER_NAME] ?: "",
            age = it[AGE] ?: 0,
            ageCheckboxChecked = it[IS_CHECKBOX_CHECKED] ?: false
        )
    }

    suspend fun clearDataStore() = context.preferenceDataStore.edit {
        it.clear()
    }
}