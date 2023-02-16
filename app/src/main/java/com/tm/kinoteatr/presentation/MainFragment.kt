package com.tm.kinoteatr.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.anyascii.AnyAscii
import com.tm.kinoteatr.R
import com.tm.kinoteatr.adapter.MainAdapter
import com.tm.kinoteatr.data.MovieRepository
import com.tm.kinoteatr.data.model.Film
import com.tm.kinoteatr.databinding.FragmentMainBinding
import com.tm.kinoteatr.viewmodel.MainViewModel
import com.tm.kinoteatr.viewmodel.MainViewModelFactory
import java.util.*

class MainFragment : Fragment(), MainAdapter.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel : MainViewModel
    lateinit var adapter: MainAdapter
    var filteredList: List<Film> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeData()

    }

    private fun initializeData() {
        var list: ArrayList<Film> = arrayListOf()
        adapter = MainAdapter()
        adapter.initializeListener(this)
        binding.movieList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.movieList.adapter = adapter
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(MovieRepository()))[MainViewModel::class.java]
        viewModel.getTopMovies()
        viewModel.mLiveTopMovies.observe(viewLifecycleOwner) {
            list = ArrayList(it.data?.films ?: arrayListOf())
            filteredList = list
            adapter.initializeList(it.data?.films ?: emptyList())
        }
        binding.movieSearchBar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filteredList = ArrayList(
                    list.filter { AnyAscii.transliterate(it.nameRu).lowercase(Locale.getDefault())
                        .contains(p0?.toString()?.lowercase(Locale.getDefault())?.trim()!!) }
                )
                adapter.initializeList(filteredList)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun onClick(position: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(
            filteredList[position]
        ))
    }
}