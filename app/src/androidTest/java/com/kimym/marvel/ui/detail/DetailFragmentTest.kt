package com.kimym.marvel.ui.detail

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kimym.marvel.NavGraphDirections
import com.kimym.marvel.R
import com.kimym.marvel.data.repository.RatingRepository
import com.kimym.marvel.ui.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentTest {
    @Inject
    lateinit var repository: RatingRepository

    private lateinit var navController: NavController

    private var hiltRule = HiltAndroidRule(this)

    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var rule: RuleChain = RuleChain.outerRule(hiltRule).around(activityScenarioRule)

    @Before
    fun setUp() {
        hiltRule.inject()
        defaultNavigateToDetailFragment()
    }

    private fun defaultNavigateToDetailFragment() {
        activityScenarioRule.scenario.onActivity { activity ->
            navController = activity.findNavController(R.id.nav_host_fragment).apply {
                navigate(NavGraphDirections.actionGlobalDetailFragment(TITLE))
            }
        }
    }

    @Test
    fun testDetailMovieTitleVerify() {
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(TITLE)))
    }

    @Test
    fun testToolbarNavigationIconClickToPopBackStack() {
        assertEquals(navController.currentDestination?.id, R.id.detailFragment)
        onView(withContentDescription(R.string.navigation_icon_content_description)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.movieFragment)
    }

    @Test
    fun testFloatingActionButtonIsDisplayedWhenRatingDoesNotExist() = runTest {
        when (repository.getRating(TITLE).firstOrNull()) {
            null -> onView(withId(R.id.fab_detail_favorite)).check(matches(isDisplayed()))
            else -> onView(withId(R.id.fab_detail_favorite)).check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun testRatingBarIsDisplayedWhenRatingIsExists() = runTest {
        when (repository.getRating(TITLE).firstOrNull()) {
            null -> onView(withId(R.id.rating_detail)).check(matches(not(isDisplayed())))
            else -> onView(withId(R.id.rating_detail)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testFloatingActionButtonClickToNavigateRatingDialogWhenRatingDoesNotExist() = runTest {
        when (repository.getRating(TITLE).firstOrNull()) {
            null -> {
                onView(withId(R.id.fab_detail_favorite)).perform(click())
                assertEquals(navController.currentDestination?.id, R.id.ratingDialog)
            }
            else -> {
                assertEquals(navController.currentDestination?.id, R.id.detailFragment)
            }
        }
    }

    companion object {
        private const val TITLE = "Iron Man"
    }
}
