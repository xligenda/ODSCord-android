package com.github.xligenda

import android.os.Bundle
import android.view.View
import com.aliucord.api.SettingsAPI
import com.aliucord.widgets.BottomSheet

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
class PluginSettings(private val settings: SettingsAPI) : BottomSheet() {

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

    }
}