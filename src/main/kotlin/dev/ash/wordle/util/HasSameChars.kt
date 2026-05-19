package dev.ash.wordle.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import java.util.Locale.getDefault

object HasSameChars {
    fun hasSameChars(guess: String, word: String): Component {
        val wordLeft = word.toCharArray()
        val guess = guess.uppercase(getDefault())
        val colors = Array(guess.length) { NamedTextColor.RED }
        for (i in guess.indices) {
            if (guess[i] == wordLeft[i]) {
                colors[i] = NamedTextColor.GREEN
                wordLeft[i] = '\u0000'
            }
        }
        for (i in guess.indices) {
            if (colors[i] == NamedTextColor.GREEN) continue
            val index = wordLeft.indexOf(guess[i])
            if (index != -1) {
                colors[i] = NamedTextColor.YELLOW
                wordLeft[index] = '\u0000'
            }
        }
        val result = Component.text()
        for (i in guess.indices) {
            result.append(Component.text(guess[i].toString()).color(colors[i]))
        }
        return result.build()
    }
}