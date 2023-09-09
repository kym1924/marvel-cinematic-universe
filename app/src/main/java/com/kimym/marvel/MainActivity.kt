package com.kimym.marvel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kimym.marvel.core.ui.jankstats.CURRENT_DESTINATION
import com.kimym.marvel.core.ui.jankstats.JankStatsLifecycleEventObserver
import com.kimym.marvel.core.ui.jankstats.getMetricsStateHolder
import com.kimym.marvel.core.ui.jankstats.putState
import com.kimym.marvel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.kimym.marvel.feature.favorite.R as favoriteR
import com.kimym.marvel.feature.movie.R as movieR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        initJankStats()
        initBottomNavigationView()
        initDecorFitsSystemWindows()
    }

    private fun initJankStats() {
        lifecycle.addObserver(JankStatsLifecycleEventObserver(window))
    }

    private fun initBottomNavigationView() {
        binding.bottomNavigation.setupWithNavController(getNavController())
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return addOnDestinationChangedListener(navHostFragment.navController)
    }

    private fun addOnDestinationChangedListener(navController: NavController): NavController {
        return navController.apply {
            addOnDestinationChangedListener { _, destination, _ ->
                setBottomNavigationVisibility(destination.id)
                putStateInMetricsStateHolder(destination.label.toString())
            }
        }
    }

    private fun setBottomNavigationVisibility(destinationId: Int) {
        when (destinationId) {
            movieR.id.movieFragment, favoriteR.id.favoriteFragment -> setBottomNavigationShowAnimation()
            else -> setBottomNavigationHideAnimation()
        }
    }

    private fun putStateInMetricsStateHolder(destinationLabel: String) {
        binding.getMetricsStateHolder().putState(CURRENT_DESTINATION, destinationLabel)
    }

    private fun setBottomNavigationShowAnimation() {
        binding.bottomNavigation.takeIf { it.visibility == View.GONE }?.let {
            it.animate().alpha(1f).setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator) {
                        super.onAnimationStart(animation)
                        it.visibility = View.VISIBLE
                    }
                }
            )
        }
    }

    private fun setBottomNavigationHideAnimation() {
        binding.bottomNavigation.takeIf { it.visibility == View.VISIBLE }?.let {
            it.animate().alpha(0f).setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        it.visibility = View.GONE
                    }
                }
            )
        }
    }

    private fun initDecorFitsSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
