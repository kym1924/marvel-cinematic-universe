package com.kimym.marvel.ui.movie

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.kimym.marvel.R
import com.kimym.marvel.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFragmentTest {
    private val context get() = getInstrumentation().targetContext

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<RecyclerView>(R.id.rv_movie).itemAnimator = null
        }
    }

    @Test
    fun testMovieFragmentToolbarTitle() {
        onView(withId(R.id.toolbar_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_movie)).check(matches(hasDescendant(withText("Marvel Cinematic Universe "))))

        openToolbarMenuAndClickPhase(R.string.phase_1)
        openToolbarMenuAndClickPhase(R.string.phase_2)
        openToolbarMenuAndClickPhase(R.string.phase_3)
        openToolbarMenuAndClickPhase(R.string.phase_4)
    }

    private fun openToolbarMenuAndClickPhase(@StringRes phase: Int) {
        openActionBarOverflowOrOptionsMenu(context)
        onView(withText(phase)).perform(click())
        onView(withId(R.id.toolbar_movie)).check(matchesToolbarTitle(phase))
    }

    private fun matchesToolbarTitle(@StringRes phase: Int): ViewAssertion? {
        return matches(hasDescendant(withText(getToolbarTitle(phase))))
    }

    private fun getToolbarTitle(@StringRes phase: Int): String {
        return String.format(
            context.getString(R.string.movie_toolbar_title),
            context.getString(phase)
        )
    }
}
