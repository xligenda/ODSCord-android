package com.github.xligenda

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.Logger
import com.aliucord.patcher.before
import com.discord.stores.StoreStream
import com.discord.restapi.RestAPIParams

@AliucordPlugin(requiresRestart = true)
class ODSClient : Plugin() {
    private val logger = Logger("ODSClient")

    init {
        settingsTab = SettingsTab(PluginSettings::class.java, SettingsTab.Type.BOTTOM_SHEET).withArgs(settings)
    }

    override fun start(context: Context) {
        patcher.before<StoreStream>("handleSendMessage") { args ->
            try {
                // Try different parameter approaches
                val message = when {
                    args.args.size > 1 && args.args[1] is String -> args.args[1] as String
                    args.args[0] is Map<*, *> -> (args.args[0] as Map<*, *>)["content"] as? String
                    else -> null
                }

                message?.let {
                    if (it.startsWith("!")) {
                        val modified = "!${it.substring(1).uppercase()}"
                        if (args.args.size > 1) {
                            args.args[1] = modified
                        } else if (args.args[0] is Map<*, *>) {
                            (args.args[0] as MutableMap<String, Any>)["content"] = modified
                        }
                    }
                }
            } catch (e: Exception) {
                logger.error("Failed to modify message", e)
            }
        }
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}