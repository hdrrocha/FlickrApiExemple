package com.example.flickraiexemple.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.flickraiexemple.databinding.FragmentPhotoDetailsBinding
import com.example.flickraiexemple.domain.viewmodel.PhotoDetailsViewModel
import com.example.flickraiexemple.router.PhotoDetailsRouter
import kotlinx.android.synthetic.main.fragment_photo_details.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PhotoDetailsFragment : Fragment() {
    private val router: PhotoDetailsRouter by inject { parametersOf(findNavController()) }
    private val photoDetailsViewModel: PhotoDetailsViewModel by viewModel()
    private val args by navArgs<PhotoDetailsFragmentArgs>()

    private lateinit var binding: FragmentPhotoDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBehavior()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = photoDetailsViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        photoDetailsViewModel.loadData(args.id)
    }

    private fun setupBehavior() {
        setHasOptionsMenu(true)

    }

    private fun setupObservers() {
        photoDetailsViewModel.data.observe(viewLifecycleOwner) { photo ->
            val width = photo?.width ?: 600
            val height = photo?.height ?: 300
            Glide.with(this)
                .load(photo?.source)
                .apply(RequestOptions().override(width, height))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPhotoDetail)
        }
    }

}
