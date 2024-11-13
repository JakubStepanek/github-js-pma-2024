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
        val NAME = stringPreferencesKey("NAME")
        val AGE = intPreferencesKey("AGE")
        val AGREEMENT = booleanPreferencesKey("AGREEMENT")
    }

    suspend fun saveToDataStore(userDetails: UserDetails) {
        context.preferenceDataStore.edit {
            it[NAME] = userDetails.name
            it[AGE] = userDetails.age
            it[AGREEMENT] = userDetails.agreement
        }
    }

    fun getFromDataStore() = context.preferenceDataStore.data.map {
        UserDetails(
            name = it[NAME] ?: "",
            age = it[AGE] ?: 0,
            agreement = it[AGREEMENT] ?: false
        )
    }

    suspend fun clearDataStore() = context.preferenceDataStore.edit {
        it.clear()
    }
}