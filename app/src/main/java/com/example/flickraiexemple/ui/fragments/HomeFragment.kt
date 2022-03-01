package com.example.flickraiexemple.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.*
import com.example.flickraiexemple.databinding.FragmentHomeBinding
import com.example.flickraiexemple.domain.viewmodel.FlickerHomeViewModel
import com.example.flickraiexemple.router.abs.HomeRouterAbs
import com.example.flickraiexemple.ui.adapters.PhotoItemsLoadStateAdapter
import com.example.flickraiexemple.ui.adapters.PhotoViewAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment: Fragment() {

    private val router: HomeRouterAbs by inject { parametersOf(findNavController()) }

    private val flickerViewModel: FlickerHomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    private val photoViewAdapter = PhotoViewAdapter()
    private val headerAdapter = PhotoItemsLoadStateAdapter { photoViewAdapter.retry() }
    private val footerAdapter = PhotoItemsLoadStateAdapter { photoViewAdapter.retry() }
    private var listener: PhotoViewAdapter.OnItemClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = flickerViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObservers()
        flickerViewModel.loadData()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupRecycler() {
        listener = object : PhotoViewAdapter.OnItemClickListener {
            override fun onItemClick(id: String) = handleOnClickEvent(id)
        }
        photoViewAdapter.onItemClickListener = listener

        photoViewAdapter.addLoadStateListener { loadStates ->
            headerAdapter.loadState = loadStates.refresh
            footerAdapter.loadState = loadStates.append
        }

        binding.rvPhotoList.run {
            layoutManager =
                StaggeredGridLayoutManager(2, RecyclerView.VERTICAL).apply {
                    gapStrategy =
                        StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                    isAutoMeasureEnabled
                }
            itemAnimator = null

            adapter = ConcatAdapter(headerAdapter, photoViewAdapter, footerAdapter)
            setHasFixedSize(true)
        }
        photoViewAdapter.submitData(lifecycle, PagingData.empty())
    }

    private fun setupObservers() {
        flickerViewModel.data.observe(viewLifecycleOwner) {
            photoViewAdapter.submitData(lifecycle, it)
        }
    }

    private fun handleOnClickEvent(id: String) {
        val args by navArgs<HomeFragmentArgs>()
        if (args.isComparing) {
            router.goBack()
        } else {
            router.goToDetails(id)
        }
    }
}
