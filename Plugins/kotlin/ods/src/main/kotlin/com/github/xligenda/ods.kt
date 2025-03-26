package com.github.xligenda

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.api.SettingsAPI
import com.aliucord.fragments.SettingsPage
import com.aliucord.settings.delegate
import com.aliucord.widgets.BottomSheet

// Aliucord Plugin annotation with parameters
@AliucordPlugin(
    requiresRestart = true /* Whether your plugin requires a restart after being installed/updated */
)
class ODSClient : Plugin() {
    private var SettingsAPI.reverse: Boolean by settings.delegate(false)
    private var SettingsAPI.threshold: Int by settings.delegate(0)

    init {
        settingsTab = SettingsTab(PluginSettings::class.java, SettingsTab.Type.BOTTOM_SHEET).withArgs(settings)
    }

    override fun start(context: Context) {
        // Your plugin initialization code here
    }

    override fun stop(context: Context) {
        // Your plugin cleanup code here
    }

    // If you need a settings page

}