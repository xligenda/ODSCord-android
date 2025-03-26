package com.github.xligenda

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.api.SettingsAPI
import com.aliucord.fragments.SettingsPage
import com.aliucord.widgets.BottomSheet

// Aliucord Plugin annotation with parameters
@AliucordPlugin(
    requiresRestart = false /* Whether your plugin requires a restart after being installed/updated */
)
class ODSClient : Plugin() {


    override fun start(context: Context) {
        // Your plugin initialization code here
    }

    override fun stop(context: Context) {
        // Your plugin cleanup code here
    }

    // If you need a settings page

}