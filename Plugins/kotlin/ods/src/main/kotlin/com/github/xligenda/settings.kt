package com.github.xligenda

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.aliucord.Utils
import com.aliucord.api.SettingsAPI
import com.aliucord.settings.delegate
import com.aliucord.utils.DimenUtils.dp
import com.aliucord.views.TextInput
import com.aliucord.widgets.BottomSheet
import com.discord.views.CheckedSetting
import com.lytefast.flexinput.R


@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
class PluginSettings(private val settings: SettingsAPI) : BottomSheet() {
    private var SettingsAPI.pluginOnlyForModServer: Boolean by settings.delegate(false)
    private var SettingsAPI.selectedConfig: String by settings.delegate("https://ods.akurise.xyz/config/share/4")
    private var SettingsAPI.verbalWarnMessage: String by settings.delegate("{{PING_USER}} Прошу вас не нарушать пункт правил {{REASON}}, ознакомьтесь с <#975425318984749066>")
    private var SettingsAPI.discordServerLinkMessage: String by settings.delegate("Ссылка на дискорд {{SERVER_LABLE}}({{SERVER_ID}}) - {{SERVER_LINK}}")
    private var SettingsAPI.giveVerbalWarnShortcut: String by settings.delegate("!")

    private var SettingsAPI.modServerId: Long by settings.delegate(684079636300300336L)
    private var SettingsAPI.usedAccounts: LongArray by settings.delegate(longArrayOf())

    private var SettingsAPI.threshold: Int by settings.delegate(1)

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

        val ctx = requireContext()

        addView(
            Utils
                .createCheckedSetting(
                    context = ctx,
                    type = CheckedSetting.ViewType.SWITCH,
                    text = "Включить плагин только на сервере ОДС",
                    subtext = "Про выключенной настройке плагин будет работать на всех дискорд серверах"
                ).apply {
                    isChecked = settings.pluginOnlyForModServer
                    setOnCheckedListener { settings.pluginOnlyForModServer = it }
                }
        )

        addView(
            TextInput(ctx).apply {
                editText.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    setText(settings.selectedConfig)
                    addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        }

                        override fun afterTextChanged(s: Editable) {
                            // fetch url
                            s.toString().let { settings.selectedConfig = it }
                        }
                    })
                }
            }
        )


        addView(
            TextInput(ctx).apply {
                editText.apply {
                    inputType = InputType.TYPE_CLASS_TEXT
                    setText(settings.selectedConfig)
                    addTextChangedListener(object : TextWatcher {
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        }

                        override fun afterTextChanged(s: Editable) {
                            s.toString().let { settings.selectedConfig = it }
                        }
                    })
                }
                setHint("Выбранный конфиг")
                8.dp.let { setPadding(it, it, it, it) }
            }


        )

        addView(
            TextView(ctx, null, 0, R.i.UiKit_Settings_Item_Addition).apply {
                text = "Ссылка на конфиг"
            }
        )


    }
}