package com.github.xligenda

import android.content.Context
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin
import com.aliucord.patcher.PreHook
import com.discord.widgets.chat.MessageContent
import com.discord.widgets.chat.input.ChatInputViewModel
import java.lang.reflect.Field

@AliucordPlugin(requiresRestart = true)
class ODSClient : Plugin() {
    private val textContentField: Field = MessageContent::class.java
        .getDeclaredField("textContent")
        .apply { isAccessible = true }

    override fun start(context: Context) {
        val sendMessageMethod = ChatInputViewModel::class.java.declaredMethods
            .first { it.name == "sendMessage" && it.parameterTypes.size == 6 }

        patcher.patch(sendMessageMethod, PreHook { callFrame ->
            try {
                val messageContent = callFrame.args[2] as MessageContent? ?: return@PreHook

                val content = textContentField.get(messageContent) as String?
                if (content.isNullOrEmpty()) return@PreHook

                textContentField.set(messageContent, content.uppercase())
            } catch (ignored: Throwable) {
                logger.error("Failed to apply CAPS LOCK", ignored)
            }
        })
    }

    override fun stop(context: Context) = patcher.unpatchAll()
}