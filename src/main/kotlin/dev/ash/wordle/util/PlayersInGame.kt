package dev.ash.wordle.util

data class GameSession(
    val word: String,
    var triesLeft: Int = 6
)

object PlayersInGame{
    private val players: MutableMap<String, GameSession> = mutableMapOf()

    fun addPlayer(uuid: String, gameSession: GameSession){
        players[uuid] = gameSession
    }

    fun isPlayerInGame(uuid: String) : Boolean{
        return players.contains(uuid)
    }

    fun getTries(uuid: String) : Int{
        return players[uuid]!!.triesLeft
    }

    fun getWord(uuid: String) : String{
        return players[uuid]!!.word
    }

    fun removePlayer(uuid: String){
        players.remove(uuid)
    }
}