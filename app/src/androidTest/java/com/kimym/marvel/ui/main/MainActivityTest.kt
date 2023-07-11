package com.kimym.marvel.ui.main

import androidx.core.view.get
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.ui.setupWithNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.marvel.MainActivity
import com.kimym.marvel.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import com.kimym.marvel.feature.favorite.R as favoriteR
import com.kimym.marvel.feature.movie.R as movieR

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var bottomNavigationView: BottomNavigationView

    private val menu get() = bottomNavigationView.menu

    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var rule: RuleChain = RuleChain
        .outerRule(HiltAndroidRule(this))
        .around(activityScenarioRule)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            bottomNavigationView = activity.findViewById(R.id.bottom_navigation)
        }
    }

    @Test
    fun testBottomNavigationViewIsDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
    }

    @Test
    fun testBottomNavigationViewMenu() {
        assertEquals(menu.size(), 2)
        assertEquals(menu[0].itemId, movieR.id.movieFragment)
        assertEquals(menu[1].itemId, favoriteR.id.favoriteFragment)
    }

    @Test
    fun testBottomNavigationViewSelection() {
        onView(withId(favoriteR.id.favoriteFragment)).perform(click())
        assertTrue(menu.findItem(favoriteR.id.favoriteFragment).isChecked)
        onView(withId(movieR.id.movieFragment)).perform(click())
        assertTrue(menu.findItem(movieR.id.movieFragment).isChecked)
    }

    @Test
    fun testBottomNavigationViewSelectionWithNavController() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        activityScenarioRule.scenario.onActivity {
            navController.setGraph(R.navigation.nav_graph)
            bottomNavigationView.setupWithNavController(navController)
        }

        assertEquals(navController.currentDestination?.id, movieR.id.movieFragment)
        onView(withId(favoriteR.id.favoriteFragment)).perform(click())
        assertEquals(navController.currentDestination?.id, favoriteR.id.favoriteFragment)
    }
}
