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
                val content = textContentField.get(messageContent) as String? ?: return@PreHook

                if (content.startsWith("!")) {
                    val parts = content.split(" ")
                    if (parts.size >= 2) {
                        val userId = parts[0].removePrefix("!")
                        val reason = parts[1]

                        if (userId.matches(Regex("\\d+"))) {
                            val result = "<@$userId> выдаю вам устное предупреждение по причине $reason"
                            textContentField.set(messageContent, result)
                        }
                    }
                }
            } catch (e: Throwable) {
                logger.error("Message processing error", e)
            }
        })
    }

    override fun stop(context: Context) = patcher.unpatchAll()
}