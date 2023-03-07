package com.example.myfavouritenovels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myfavouritenovels.databinding.FragmentConfirmDialogBinding


class ConfirmDialogFragment : Fragment() {

    lateinit var dialogTitle: String
    var dialogDescription: String? = null

    private var _binding: FragmentConfirmDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvDialogTitle: TextView
    private lateinit var tvDialogDescription: TextView

    companion object {
        const val DIALOG_TITLE = "dialog_title"
        const val DIALOG_DESCRIPTION = "dialog_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvDialogTitle = binding.tvConfirmTitle
        tvDialogDescription = binding.tvConfirmSubtitle

        if (savedInstanceState != null) {
            dialogTitle = savedInstanceState.getString(DIALOG_TITLE) ?: "Are you sure?"
            dialogDescription = savedInstanceState.getString(DIALOG_DESCRIPTION)

            tvDialogTitle.text = dialogTitle
            if (dialogDescription != null){
                tvDialogDescription.text = dialogDescription
                tvDialogDescription.visibility = View.VISIBLE
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(DIALOG_TITLE, dialogTitle)
        outState.putString(DIALOG_DESCRIPTION, dialogDescription)
    }

    interface onConfirmDialogListener {
        fun onConfirm(isConfirmed: Boolean)
    }
}