package com.cmpt395.deferralapp.state

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

//Used to update and retrieve app settings.
class SettingsManager(context: Context) {
    private val dataStore = context.applicationContext.dataStore
    private val darkModeKey = booleanPreferencesKey("dark_mode_enabled")

    //Retrieves the dark mode setting.
    val isDarkModeEnabled: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[darkModeKey] ?: false
        }

    //Updates the dark mode setting.
    suspend fun setDarkMode(isEnabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[darkModeKey] = isEnabled
        }
    }
}