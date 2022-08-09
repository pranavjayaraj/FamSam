package com.pranavjayaraj.widget.containerview

enum class Constants(var key: String = "") {
    SMALL_DISPLAY_CARD("HC1"),
    BIG_DISPLAY_CARD("HC3"),
    IMAGE_CARD("HC5"),
    SMALL_CARD_WITH_ARROW("HC6"),
    DYNAMIC_WIDTH_CARD("HC9");

    companion object {
        fun get(key: String): Constants? {
            return values().firstOrNull { it.key == key }
        }

    }
}