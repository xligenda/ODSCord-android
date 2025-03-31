package com.github.xligenda

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.api.SettingsAPI
import com.aliucord.fragments.SettingsPage
import com.aliucord.settings.delegate
import com.aliucord.widgets.BottomSheet
import com.aliucord.PluginManager
import com.aliucord.api.CommandsAPI
import com.discord.api.commands.ApplicationCommandType
import com.aliucord.Http
import com.aliucord.Utils
import com.aliucord.patcher.before
import com.discord.models.message.Message
import com.discord.stores.StoreStream
import com.discord.utilities.rest.RestAPI
import com.discord.utilities.time.ClockFactory
import com.discord.api.channel.Channel
import com.discord.api.message.attachment.MessageAttachment
import com.discord.restapi.RestAPIParams

@AliucordPlugin(
    requiresRestart = true
)
class ODSClient : Plugin() {
    init {
        settingsTab = SettingsTab(PluginSettings::class.java, SettingsTab.Type.BOTTOM_SHEET).withArgs(settings)
    }

    override fun start(context: Context) {
        // Patch the message sending function
        patcher.before<StoreStream>("handleSendMessage") { args ->
            val message = args.args[1] as String
            if (message.startsWith("!")) {
                val capitalizedMessage = message.substring(1).uppercase()
                args.args[1] = "!$capitalizedMessage"
            }
        }
    }

    override fun stop(context: Context) {
        // Remove all patches when the plugin is stopped
        patcher.unpatchAll()
    }
}