package com.kimym.marvel.data.model

import com.kimym.marvel.R

enum class Phase(val str: String) {
    ALL(""),
    ONE("Phase 1"),
    TWO("Phase 2"),
    THREE("Phase 3"),
    FOUR("Phase 4");

    companion object {
        val map = mapOf(
            R.id.filter_phase_all to ALL,
            R.id.filter_phase_1 to ONE,
            R.id.filter_phase_2 to TWO,
            R.id.filter_phase_3 to THREE,
            R.id.filter_phase_4 to FOUR
        )
    }
}
