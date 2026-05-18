package dev.ash.wordle.commands

import dev.ash.wordle.util.Dictionary
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
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
        when{
            sender !is Player ->{
                sender.sendMessage(Component.text("Эту команду может использовать только игрок.").
                color(NamedTextColor.RED))
                return true
            }
            args.size != 1 || args[0] != "start" ->{
                sender.sendMessage(Component.text("Использование /wordle <start>").
                color(NamedTextColor.RED))
                return true
            }
        }
        sender.sendMessage(Component.text("Игра началась! Удачи").color(NamedTextColor.GREEN))
        sender.sendMessage(Component.text(Dictionary.words.random()))
        return true
    }
}