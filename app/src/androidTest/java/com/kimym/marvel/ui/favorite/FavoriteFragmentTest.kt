package com.kimym.marvel.ui.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.marvel.R
import com.kimym.marvel.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteFragmentTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .selectedItemId = R.id.favoriteFragment
        }
    }

    @Test
    fun testToolbarTitleIsFavoriteMarvelMovies() {
        with(onView(withId(R.id.toolbar_favorite))) {
            check(matches(isDisplayed()))
            check(matches(hasDescendant(withText("Favorite Marvel Movies"))))
        }
    }

    @Test
    fun testFavoriteEmptyTestIsDisplayed() {
        onView(withText("Favorites are empty.")).check(matches(isDisplayed()))
    }
}
