package com.example.myfavouritenovels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myfavouritenovels.databinding.FragmentAboutBinding

class AboutFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    var description: String? = null
    private lateinit var tvAuthorsDescription: TextView
    private lateinit var tvAuthorsEmail: TextView

    companion object {
        const val EXTRA_EMAIL = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.aboutToolbar.apply {
            setNavigationIcon(R.drawable.ic_back_arrow)
            setNavigationOnClickListener {
                onClick(this)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvAuthorsEmail = binding.tvAuthorsEmail
        tvAuthorsDescription = binding.tvAuthorsDescription

        if (savedInstanceState != null) {
            description = savedInstanceState.getString(EXTRA_DESCRIPTION)
        }

        if (arguments != null) {
            tvAuthorsEmail.text = arguments?.getString(EXTRA_EMAIL)
            tvAuthorsDescription.text = description
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.about_toolbar) {
            parentFragmentManager.beginTransaction().apply {
                remove(this@AboutFragment)
                commit()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_DESCRIPTION, description)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}