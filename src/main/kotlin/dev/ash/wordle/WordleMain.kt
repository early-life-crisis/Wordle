package dev.ash.wordle

import dev.ash.wordle.commands.WordleCommand
import dev.ash.wordle.listeners.ChatListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class WordleMain : JavaPlugin() {

    override fun onEnable() {
        getCommand("wordle")!!.setExecutor(WordleCommand())
        Bukkit.getPluginManager().registerEvents(ChatListener(), this)
    }

    override fun onDisable() {

    }

}