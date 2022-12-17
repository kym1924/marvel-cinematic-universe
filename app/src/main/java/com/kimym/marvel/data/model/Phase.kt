package com.kimym.marvel.data.model

import com.kimym.marvel.R

enum class Phase {
    ALL, ONE, TWO, THREE, FOUR;

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
