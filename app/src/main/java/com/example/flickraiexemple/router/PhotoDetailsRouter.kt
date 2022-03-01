package com.example.flickraiexemple.router

import androidx.navigation.NavController
import com.example.flickraiexemple.router.abs.PhotoDetailsRouterAbs

class PhotoDetailsRouter(
    private val navController: NavController
) : PhotoDetailsRouterAbs {

    override fun goBack() {
        navController.navigateUp()
    }
}
