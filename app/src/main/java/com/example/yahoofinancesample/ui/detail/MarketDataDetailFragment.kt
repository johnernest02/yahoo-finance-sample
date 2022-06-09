package com.example.yahoofinancesample.ui.detail

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.yahoofinancesample.databinding.FragmentItemDetailBinding
import com.example.yahoofinancesample.ui.list.MarketDataListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
@AndroidEntryPoint
class MarketDataDetailFragment : Fragment() {

    private val viewModel: MarketDataDetailViewModel by viewModels()

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        setupObservers()
        return rootView
    }

    private fun setupObservers() {
        viewModel.getStockSummary().observe(viewLifecycleOwner) { stockSummary ->
            toolbarLayout?.title =
                stockSummary.quoteType.shortName + "(${stockSummary.quoteType.symbol})"

            _binding?.regularMarketPrice?.text = stockSummary.price.regularMarketPrice.fmt
            _binding?.regularMarketChange?.text = stockSummary.price.regularMarketChange.fmt
            _binding?.previousCloseValue?.text = stockSummary.price.regularMarketPreviousClose.fmt
            _binding?.openValue?.text = stockSummary.price.regularMarketOpen.fmt
            _binding?.volumeValue?.text = stockSummary.price.regularMarketVolume.fmt
            _binding?.daysRangeValue?.text =
                "${stockSummary.summaryDetail.dayLow.fmt} - ${stockSummary.summaryDetail.dayLow.fmt}"
            _binding?.fiftytwoWeekValue?.text =
                "${stockSummary.summaryDetail.fiftyTwoWeekLow.fmt} - ${stockSummary.summaryDetail.fiftyTwoWeekHigh.fmt}"
            _binding?.avgVolumeValue?.text = "${stockSummary.summaryDetail.averageVolume.fmt}"
        }
    }

    companion object {
        const val ARG_SYMBOL = "symbol"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}