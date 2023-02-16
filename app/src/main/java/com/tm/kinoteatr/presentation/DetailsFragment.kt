package com.tm.kinoteatr.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tm.kinoteatr.R
import com.tm.kinoteatr.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    val args: DetailsFragmentArgs by navArgs();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        binding = FragmentDetailsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeScreen()
    }

    private fun initializeScreen() {
        Glide.with(requireContext())
            .load(args.film.posterUrl)
            .into(binding.imageView)
        binding.title.text = args.film.nameRu
        binding.rating.text = args.film.rating
        binding.year.text = args.film.year
    }
}