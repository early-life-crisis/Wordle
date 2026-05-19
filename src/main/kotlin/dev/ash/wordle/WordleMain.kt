package dev.ash.wordle

import dev.ash.wordle.commands.WordleCommand
import dev.ash.wordle.listeners.ChatListener
import dev.ash.wordle.listeners.PlayerQuitListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class WordleMain : JavaPlugin() {

    override fun onEnable() {
        val wordleCommand = getCommand("wordle")!!

        wordleCommand.setExecutor(WordleCommand())
        wordleCommand.tabCompleter = WordleCommand()
        Bukkit.getPluginManager().registerEvents(ChatListener(), this)
        Bukkit.getPluginManager().registerEvents(PlayerQuitListener(), this)
    }

    override fun onDisable() {

    }

}