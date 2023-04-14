package net.eratiem.kotlinprovider

import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import java.util.logging.Logger
import javax.inject.Inject

@Plugin(
    id = "kotlinprovider", name = "KotlinProvider", version = "1.7.0",
    description = "EraTiem-Networks plugin to provide Kotlin for other Plugins", authors = ["Motzkiste"]
)
class KotlinProviderPlugin @Inject constructor(
    private val logger: Logger
) {
    private val name = "KotlinProvider"

    @Subscribe(order = PostOrder.FIRST)
    fun onEnable(event: ProxyInitializeEvent) {
        logger.info("Kotlin can now be used!")
    }

    @Subscribe(order = PostOrder.LAST)
    fun onDisable(event: ProxyShutdownEvent) {
        logger.info("Kotlin is no longer useable!")
    }
}
