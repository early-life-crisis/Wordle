package dev.ash.wordle.listeners

import dev.ash.wordle.util.PlayersInGame
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener : Listener {
    fun onPlayerQuit(e: PlayerQuitEvent){
        PlayersInGame.removePlayer(e.player.uniqueId.toString())
    }
}