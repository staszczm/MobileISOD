package com.example.isodnotify.tiles

class NewsTile {

    private var titlesList = mutableListOf<String>()

    constructor(newsTitles : List<String> ) {

        for(title in newsTitles) {
            addToList(title)
        }

    }

    fun getTitlesList(): MutableList<String> {
        return titlesList
    }

    private fun addToList(title: String) {
        titlesList.add(title)
    }

}