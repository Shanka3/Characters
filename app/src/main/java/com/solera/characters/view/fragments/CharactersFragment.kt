package com.solera.characters.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solera.characters.R
import com.solera.characters.adapter.CharacterAdapter
import com.solera.characters.model.Characters
import com.solera.characters.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private val viewModel: CharactersViewModel by viewModels()
    var page = 1
    var isLoading = false
    private val pageLimit = 20
    var total = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)

        recyclerView = view.findViewById(R.id.rvCharacters)
        progressBar = view.findViewById(R.id.progressBar)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = CharacterAdapter(childFragmentManager)
        recyclerView.adapter = adapter

        viewModel.charactersLiveData.observe(viewLifecycleOwner) { characters ->
            characters?.let { characterList ->
                getData(characterList)
                recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0) {
                            val visibleItemCount = layoutManager.childCount
                            val pastVisibleItem =
                                layoutManager.findFirstCompletelyVisibleItemPosition()
                            total = adapter.itemCount

                            if (!isLoading) {
                                if ((visibleItemCount + pastVisibleItem) >= total) {
                                    page++
                                    getData(characterList)
                                }
                            }
                        }
                        super.onScrolled(recyclerView, dx, dy)
                    }
                })
            }
        }
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(characterList: Characters) {
        isLoading = true
        progressBar.visibility = View.VISIBLE
        val start = (page - 1) * pageLimit
        val end = (page) * pageLimit

        for (i in start..end) {
            adapter.setData(characterList.take(i))
        }

        Handler().postDelayed({
            if (::adapter.isInitialized) {
                adapter.notifyDataSetChanged()
            } else {
                adapter = CharacterAdapter(childFragmentManager)
                recyclerView.adapter = adapter
            }
            isLoading = false
            progressBar.visibility = View.GONE
        }, 5)
    }
}
