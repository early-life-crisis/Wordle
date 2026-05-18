package dev.ash.wordle

import dev.ash.wordle.commands.WordleCommand
import org.bukkit.plugin.java.JavaPlugin

class WordleMain : JavaPlugin() {

    override fun onEnable() {
        getCommand("wordle")!!.setExecutor(WordleCommand())
    }

    override fun onDisable() {

    }

}