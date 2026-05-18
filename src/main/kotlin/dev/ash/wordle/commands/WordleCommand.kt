package dev.ash.wordle.commands

import dev.ash.wordle.util.Dictionary
import dev.ash.wordle.util.GameSession
import dev.ash.wordle.util.PlayersInGame
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minecraft.commands.arguments.UuidArgument.uuid
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WordleCommand : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player){
            sender.sendMessage(Component.text("Эту команду может использовать только игрок.").
            color(NamedTextColor.RED))
            return true
        }
        val uuid = sender.uniqueId.toString()
        val isInGame = PlayersInGame.isPlayerInGame(uuid)
        when{
            args.isEmpty() ->{
                sender.sendMessage(Component.text("Использование - /worlde start|stop").
                color(NamedTextColor.RED))
                return true
            }

            args[0] == "stop" -> {
                if (PlayersInGame.isPlayerInGame(uuid)){
                    PlayersInGame.removePlayer(uuid)
                    sender.sendMessage(Component.text("Вы успешно вышли из игры.").
                    color(NamedTextColor.GREEN))
                    return true
                }
                sender.sendMessage(Component.text("Вы не в игре.").
                color(NamedTextColor.RED))
                return true
            }

            args.size != 1 || args[0] != "start" ->{
                sender.sendMessage(Component.text("Использование /wordle <start>").
                color(NamedTextColor.RED))
                return true
            }

            isInGame ->{
                sender.sendMessage(Component.text("Вы уже в игре.").
                color(NamedTextColor.RED))
                return true
            }
        }

        val word = Dictionary.words.random()
        sender.sendMessage(Component.text(word))
        PlayersInGame.addPlayer(uuid, GameSession(word))

        sender.sendMessage(Component.text("Игра началась! Удачи").color(NamedTextColor.GREEN))
        return true
    }
}