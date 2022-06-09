package com.example.yahoofinancesample.ui.list

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.yahoofinancesample.R
import com.example.yahoofinancesample.databinding.FragmentItemListBinding
import com.example.yahoofinancesample.databinding.ItemListContentBinding
import com.example.yahoofinancesample.ui.detail.MarketDataDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.yahoofinancesample.service.responsemodels.Result as MarketData

@AndroidEntryPoint
class MarketDataListFragment : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: MarketDataListViewModel by viewModels()

    private lateinit var adapter: MarketDataAdapter

    private var currentFilter: String? = null

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView: SearchView? = binding.searchView
        val recyclerView: RecyclerView = binding.itemList
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupViews(searchView, recyclerView, itemDetailFragmentContainer)
        setUpObservers()
    }

    private fun setupViews(
        searchView: SearchView?,
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {
        searchView?.setOnQueryTextListener(this)
        adapter = MarketDataAdapter(
            arrayListOf(), itemDetailFragmentContainer
        )
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter.filter.filter(query)
        currentFilter = query
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        currentFilter = newText
        return false
    }

    private fun setUpObservers() {
        viewModel.getMarketData().observe(viewLifecycleOwner) { data ->
            adapter.addData(data)
            adapter.filter.filter(currentFilter)
        }
    }

    class MarketDataAdapter(
        var marketSummaryList: ArrayList<MarketData>,
        private val itemDetailFragmentContainer: View?
    ) : RecyclerView.Adapter<MarketDataAdapter.ViewHolder>(), Filterable {

        var marketSummaryListFiltered: ArrayList<MarketData> = ArrayList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = marketSummaryListFiltered[position]
            holder.shortNameText.text = item.shortName
            holder.regularMarketPriceText.text = item.regularMarketPreviousClose?.fmt

            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val item = itemView.tag as MarketData
                    val bundle = Bundle()
                    bundle.putString(
                        MarketDataDetailFragment.ARG_SYMBOL,
                        item.symbol
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }
            }
        }

        override fun getItemCount() = marketSummaryListFiltered.size

        fun addData(list: List<MarketData>) {
            marketSummaryList = list as ArrayList<MarketData>
            marketSummaryListFiltered = marketSummaryList
            notifyDataSetChanged()
        }

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val shortNameText: TextView = binding.shortNameText
            val regularMarketPriceText: TextView = binding.regularMarketPriceText
        }

        override fun getFilter(): Filter {
            return object : Filter() {
                override fun performFiltering(constraint: CharSequence?): FilterResults {
                    val charString = constraint?.toString() ?: ""
                    if (charString.isEmpty()) marketSummaryListFiltered = marketSummaryList else {
                        val filteredList = ArrayList<MarketData>()
                        marketSummaryList
                            .filter {
                                (it.shortName?.contains(constraint!!) == true)
                            }
                            .forEach { filteredList.add(it) }
                        marketSummaryListFiltered = filteredList
                    }
                    return FilterResults().apply { values = marketSummaryListFiltered }
                }

                override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                    marketSummaryListFiltered = if (results?.values == null)
                        ArrayList()
                    else
                        results.values as ArrayList<MarketData>
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}