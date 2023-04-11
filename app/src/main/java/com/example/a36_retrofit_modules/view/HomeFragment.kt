package com.example.a36_retrofit_modules.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.a36_retrofit_modules.adapters.HorizontalMarginItemDecoration
import com.example.a36_retrofit_modules.adapters.ViewPaggerMovieAdapter
import com.example.a36_retrofit_modules.adapters.pagging.MovieAdapter
import com.example.a36_retrofit_modules.adapters.paggingFooter.MovieLoadStateAdapter
import com.example.a36_retrofit_modules.R
import com.example.a36_retrofit_modules.databinding.FragmentHomeBinding
import com.example.a36_retrofit_modules.viewmodel.MovieViewModel
import com.example.domain.model.MovieItem
import com.example.domain.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Math.abs


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieAdapter2: ViewPaggerMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        movieAdapter = MovieAdapter { actionUI ->
            when (actionUI) {
                is ActionUI.Click -> {
                    val bundle = bundleOf("popularMovie" to actionUI.movieItem)
                    this.findNavController()
                        .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                }
                is ActionUI.Delete -> {}
                is ActionUI.Favorite -> {}
                is ActionUI.Share -> {}


            }

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

            //bind the LoadStateAdapter with the movieAdapter
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
        }

        movieAdapter2 = ViewPaggerMovieAdapter()
        setObservers()
        setViewPagger2()
        return binding.root
    }


    private fun setObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
// select any page you want as your starting page
            val currentPageIndex = 2
            binding.viewPager.currentItem = currentPageIndex
            movieViewModel.popularMovies.collectLatest {
                movieAdapter.submitData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.lifecycle.whenStarted {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {


                movieViewModel.stateTwo.collect { uiStatedos ->
Log.d("tag",uiStatedos.loadingBar.toString())
                    if (!uiStatedos.loadingBar) {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    } else {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }


                    uiStatedos.topRatedMovies?.let {
                        when (it) {
                            is DataState.Data -> {
                                movieAdapter2.submitList(it.data)
                            }
                            is DataState.Error -> {

                            }
                        }
                    }



                }



//            }
}

        }
    }

    //    private fun onQueryTextListener(adapter: MovieAdapter) =
//        object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // your text view here
//                //                                textView.setText(newText)
//                if (newText != null) {
//                    adapter.filter(newText)
//                }
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                //                                textView.setText(query)
//
//                return true
//            }
//        }
//            binding.circleView.spin() // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//            binding.circleView.mAnimationDuration=800
//            binding.circleView.setUnitVisible(false)
//            binding.circleView.setTextMode(TextMode.TEXT)
//            binding.circleView.setShowTextWhileSpinning(true)
//            binding.circleView.setText("Loading...")
//
    private fun setViewPagger2() {
        // MyRecyclerViewAdapter is an standard RecyclerView.Adapter :)
//    binding.viewPager.adapter = MyRecyclerViewAdapter()
        binding.viewPager.adapter = movieAdapter2
// You need to retain one page on each side so that the next and previous items are visible
        binding.viewPager.offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }

        binding.viewPager.setPageTransformer(pageTransformer)



// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)

    }

    private fun setUpViewPager() {


        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 1
        binding.viewPager.currentItem = currentPageIndex

        val pageMargin =
            resources.getDimensionPixelOffset(R.dimen.viewpager_current_item_horizontal_margin)
                .toFloat()
        val pageOffset =
            resources.getDimensionPixelOffset(R.dimen.viewpager_next_item_visible).toFloat()

        binding.viewPager.setPageTransformer { page, position ->
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            if (position < -1) {
                page.translationX = -myOffset
            } else if (position <= 1) {
                val scaleFactor =
                    0.7f.coerceAtLeast(1 - kotlin.math.abs(position - 0.14285715f))
                page.translationX = myOffset
                page.scaleY = scaleFactor
                page.alpha = scaleFactor
            } else {
                page.alpha = 0F
                page.translationX = myOffset
            }
        }
    }

}

sealed interface ActionUI {
    class Click(val movieItem: MovieItem) : ActionUI
    class Favorite(val movieItem: MovieItem) : ActionUI
    class Share(val movieItem: MovieItem) : ActionUI
    class Delete(val movieItem: MovieItem) : ActionUI
}