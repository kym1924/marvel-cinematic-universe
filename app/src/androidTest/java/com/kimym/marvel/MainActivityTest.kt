package com.kimym.marvel

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kimym.marvel.ui.main.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var bottomNavigationView: BottomNavigationView

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            bottomNavigationView = activity.findViewById(R.id.bottom_navigation)
        }
    }

    @Test
    fun bottomNavigationDisplayedTest() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigationViewMenuTest() {
        val menu = bottomNavigationView.menu
        assertEquals(menu.size(), 2)
        assertEquals(menu[0].itemId, R.id.movieFragment)
        assertEquals(menu[1].itemId, R.id.favoriteFragment)
    }

    @Test
    fun bottomNavigationViewSelectionTest() {
        assertTrue(bottomNavigationView.menu.findItem(R.id.movieFragment).isChecked)
        onView(withId(R.id.favoriteFragment)).perform(click())
        assertTrue(bottomNavigationView.menu.findItem(R.id.favoriteFragment).isChecked)
    }

    @Test
    fun bottomNavigationViewWithNavControllerTest() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        activityScenarioRule.scenario.onActivity {
            navController.setGraph(R.navigation.nav_graph)
            bottomNavigationView.setupWithNavController(navController)
        }

        assertEquals(navController.currentDestination?.id, R.id.movieFragment)
        onView(withId(R.id.favoriteFragment)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.favoriteFragment)
    }
}
