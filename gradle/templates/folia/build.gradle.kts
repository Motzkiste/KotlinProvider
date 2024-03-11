import java.io.File.separator

plugins {
  alias(libs.plugins.paperweight.userdev)
  alias(libs.plugins.run.paper)
}

val mcVersionRegex = Regex("^\\d+\\.\\d+\\.\\d+")
val mcApiVersionRegex = Regex("^\\d+\\.\\d+")

dependencies {
  compileOnly(libs.minecraft.server.folia)
  paperweight.foliaDevBundle(libs.versions.folia.get())

  findProject(":util")?.let { implementation(it) }
}

rootProject.publishing {
  publications {
    val block: MavenPublication.() -> Unit = {
      artifact(tasks.reobfJar) {
        classifier = "folia"
      }

      artifactId = rootProject.name
    }

    if (findByName("maven") != null) {
      named("maven", block)
    } else {
      create("maven", block)
    }
  }
}

tasks {
  runPaper.folia.registerTask {
    minecraftVersion(mcVersionRegex.find(libs.versions.folia.get())!!.value)
  }

  reobfJar {
    outputJar.set(project.layout.buildDirectory.file("libs$separator${rootProject.name}-${rootProject.version}-folia.jar"))
  }

  withType<Copy> {
    val props = listOf(
      "main-class",
      "description",
      "load",
      "authors",
      "dependencies",
      "soft-dependencies",
      "load-before",
      "commands"
    ).associate {
      it.replace('-', '_') to getPluginProperty(it)
    }.toMutableMap()

    props["name"] = rootProject.name
    props["version"] = "${rootProject.version}"
    props["api_version"] = mcApiVersionRegex.find(libs.versions.paper.get())?.value
    props["main_class"] = "${rootProject.group}.${rootProject.name.lowercase()}.${props["main_class"]}"

    filesMatching("plugin.yml") {
      expand(props.map { "plugin_${it.key}" to it.value }.toMap())
    }
  }
}

sourceSets {
  main {
    java.setSrcDirs(listOf<String>())
  }
  test {
    java.setSrcDirs(listOf<String>())
  }
}

private fun getPluginProperty(key: String) = System.getProperty("plugin.$key", "")