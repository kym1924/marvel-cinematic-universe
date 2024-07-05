package com.kimym.marvel

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kimym.marvel.core.ui.jankstats.CURRENT_DESTINATION
import com.kimym.marvel.core.ui.jankstats.JankStatsLifecycleEventObserver
import com.kimym.marvel.core.ui.jankstats.getMetricsStateHolder
import com.kimym.marvel.core.ui.jankstats.putState
import com.kimym.marvel.databinding.ActivityMainBinding
import com.kimym.marvel.domain.model.Appearance
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import com.kimym.marvel.feature.favorite.R as favoriteR
import com.kimym.marvel.feature.movie.R as movieR
import com.kimym.marvel.feature.setting.R as settingR

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        initJankStats()
        initBottomNavigationView()
        initDecorFitsSystemWindows()
        initAppearanceCollect()
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
        binding.bottomNavigation.visibility = when (destinationId) {
            movieR.id.movieFragment, favoriteR.id.favoriteFragment, settingR.id.settingDialog -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun putStateInMetricsStateHolder(destinationLabel: String) {
        binding.root.getMetricsStateHolder().putState(CURRENT_DESTINATION, destinationLabel)
    }

    private fun initDecorFitsSystemWindows() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun initAppearanceCollect() {
        lifecycleScope.launch {
            vm.appearance
                .filterNotNull()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { appearance ->
                    when (appearance) {
                        Appearance.LIGHT -> setAppearance(AppCompatDelegate.MODE_NIGHT_NO)
                        Appearance.DARK -> setAppearance(AppCompatDelegate.MODE_NIGHT_YES)
                        Appearance.FOLLOW_SYSTEM -> setAppearance(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
        }
    }

    private fun setAppearance(appCompatDelegate: Int) {
        AppCompatDelegate.setDefaultNightMode(appCompatDelegate)
    }
}
