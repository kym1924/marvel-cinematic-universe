package com.kimym.marvel.ui.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import com.kimym.marvel.core.ui.R as uiR
import com.kimym.marvel.feature.movie.R as movieR

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MovieFragmentTest {
    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var rule: RuleChain = RuleChain
        .outerRule(HiltAndroidRule(this))
        .around(activityScenarioRule)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<RecyclerView>(movieR.id.rv_movie).itemAnimator = null
        }
    }

    @Test
    fun testDefaultToolbarTitleIsMarvelCinematicUniverse() {
        with(onView(withId(movieR.id.toolbar_movie))) {
            check(matches(isDisplayed()))
            check(matches(hasDescendant(withText(uiR.string.movie_toolbar_title))))
        }
    }

    @Test
    fun testNavigateToDetailFragment() {
        with(onView(withId(movieR.id.rv_movie))) {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            check(doesNotExist())
        }
    }
}
