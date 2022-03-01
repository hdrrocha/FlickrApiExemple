package com.example.flickraiexemple.router

import androidx.navigation.NavController
import com.example.flickraiexemple.router.abs.HomeRouterAbs
import com.example.flickraiexemple.ui.fragments.HomeFragmentDirections

class HomeRouter(
    private val navController: NavController
) : HomeRouterAbs {

    override fun goToDetails(id: String) {
        val directions = HomeFragmentDirections.fragmentHomeToFragmentPhotoDetails(id)
        navController.navigate(directions)
    }

    override fun goBack() {
        navController.navigateUp()
    }
}
