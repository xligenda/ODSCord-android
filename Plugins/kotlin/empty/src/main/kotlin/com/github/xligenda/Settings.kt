package com.github.xligenda

import android.os.Bundle
import android.view.View
import com.aliucord.api.SettingsAPI
import com.aliucord.settings.delegate
import com.aliucord.widgets.BottomSheet

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
class PluginSettings(private val settings: SettingsAPI) : BottomSheet() {
    private var SettingsAPI.reverse: Boolean by settings.delegate(false)
    private var SettingsAPI.threshold: Int by settings.delegate(1)

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

    }
}