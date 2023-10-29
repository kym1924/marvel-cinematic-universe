package com.kimym.marvel.ui.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.marvel.MainActivity
import com.kimym.marvel.R
import com.kimym.marvel.domain.repository.MovieRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject
import com.kimym.marvel.core.ui.R as uiR
import com.kimym.marvel.feature.favorite.R as favoriteR

@HiltAndroidTest
class FavoriteFragmentTest {
    @Inject
    lateinit var repository: MovieRepository

    private var hiltRule = HiltAndroidRule(this)

    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var rule: RuleChain = RuleChain.outerRule(hiltRule).around(activityScenarioRule)

    @Before
    fun setUp() {
        hiltRule.inject()
        bottomNavigationViewDefaultSelectedItem()
    }

    private fun bottomNavigationViewDefaultSelectedItem() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .selectedItemId = uiR.id.favorite_nav_graph
        }
    }

    @Test
    fun testToolbarTitleIsFavoriteMarvelMovies() {
        with(onView(withId(favoriteR.id.toolbar_favorite))) {
            check(matches(isDisplayed()))
            check(matches(hasDescendant(withText(uiR.string.favorite_toolbar_title))))
        }
    }

    @Test
    fun testEmptyTextForFavoritesIsEmptyOrRecyclerViewForFavoritesIsNotEmpty() = runTest {
        when (repository.getMovieAndRatings().first().isEmpty()) {
            true -> onView((withId(favoriteR.id.tv_favorite_empty))).check(matches(isDisplayed()))
            false -> onView((withId(favoriteR.id.rv_favorite))).check(matches(isDisplayed()))
        }
    }
}
