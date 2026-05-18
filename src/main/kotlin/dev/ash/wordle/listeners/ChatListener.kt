package dev.ash.wordle.listeners

import dev.ash.wordle.util.PlayersInGame
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ChatListener : Listener {
    @EventHandler
    fun onPlayerChat(e: AsyncChatEvent){
        val uuid = e.player.uniqueId.toString()
        val player = e.player

        if (PlayersInGame.isPlayerInGame(uuid)){
            val message = PlainTextComponentSerializer.plainText().serialize(e.message())
            when {
                message == PlayersInGame.getWord(uuid) ->{
                    PlayersInGame.removePlayer(uuid)
                    player.sendMessage(Component.text("Вы угадали слово.").
                    color(NamedTextColor.GREEN))
                }
                else -> player.sendMessage(Component.text("Попробуйте ещё раз!").
                color(NamedTextColor.RED))
            }

        }
    }
}