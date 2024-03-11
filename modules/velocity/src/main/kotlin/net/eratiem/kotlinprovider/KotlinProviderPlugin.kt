package net.eratiem.kotlinprovider

import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Dependency
import com.velocitypowered.api.plugin.Plugin
import org.slf4j.Logger
import javax.inject.Inject

@Plugin(
  id = "kotlinprovider",
  name = "KotlinProvider",
  version = "1.9.23",
  description = "EraTiem-Networks plugin to provide Kotlin for other Plugins",
  authors = [
    "Motzkiste"
  ],
  dependencies = [
    Dependency(id = "kotlinprovider", optional = false)
  ]
)
  }
}