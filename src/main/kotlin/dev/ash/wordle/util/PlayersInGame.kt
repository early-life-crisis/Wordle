package dev.ash.wordle.util

object PlayersInGame {
    private val players: MutableMap<String, Int> = mutableMapOf()

    fun addPlayer(uuid: String){
        players[uuid] = 6
    }

    fun isPlayerInGame(uuid: String) : Boolean{
        return players.containsKey(uuid)
    }

    fun getTries(uuid: String) : Int{
        return players[uuid] ?: 0
    }

    fun removePlayer(uuid: String){
        players.remove(uuid)
    }
}