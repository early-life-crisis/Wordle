package dev.ash.wordle.listeners

import dev.ash.wordle.util.HasSameChars
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
            val word = PlayersInGame.getWord(uuid)

            when{
                PlayersInGame.getTries(uuid) == 1 && !message.equals(word, ignoreCase = true) ->{
                    player.sendMessage(Component.text("Вы не смогли угадать слово, попытки закончились.").
                    color(NamedTextColor.RED))
                    PlayersInGame.removePlayer(uuid)
                }
                message.equals(word, ignoreCase = true) ->{
                    player.sendMessage(Component.text("Вы угадали слово.").
                    color(NamedTextColor.GREEN))
                    PlayersInGame.removePlayer(uuid)
                }
                else -> {
                    player.sendMessage(HasSameChars.hasSameChars(message, word))
                    PlayersInGame.removeTry(uuid)
                    player.sendMessage(Component.text("Неправильное слово, осталось ${PlayersInGame.getTries(uuid)} попыток.").
                    color(NamedTextColor.RED))
                }

            }
        }
    }
}