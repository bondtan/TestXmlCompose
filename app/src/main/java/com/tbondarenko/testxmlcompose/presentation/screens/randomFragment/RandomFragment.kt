package com.tbondarenko.testxmlcompose.presentation.screens.randomFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.tbondarenko.testxmlcompose.R
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.databinding.FragmentRandomBinding
import com.tbondarenko.testxmlcompose.presentation.screens.utils.fromHtml
import com.tbondarenko.testxmlcompose.presentation.screens.utils.showErrorInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFragment : Fragment(R.layout.fragment_random) {

    private var _binding: FragmentRandomBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel by viewModels<RandomViewModel>()

    private lateinit var errorDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRandomEmoji()
        binding.buttonRandomEmoji.setOnClickListener {
            getRandom()
        }
    }

    private fun loadRandomEmoji() {
        getRandom()
        viewModel.result.observe(this.viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setViewVisible()
                    with(binding) {
                        textviewRandomEmoji.text = result.data.htmlCode.first().fromHtml()
                        textviewRandomNameEmoji.text =
                            result.data.name.uppercase().substringBefore("≊")
                    }
                }
                is NetworkResult.Error -> {
                    if (::errorDialog.isInitialized) errorDialog.dismiss()
                    errorDialog = showErrorInFragment(
                        context = requireContext(),
                        title = result.error,
                        message = result.message,
                        setViewVisibility = { setViewVisible() },
                        tryAgain = { getRandom() }
                    )
                }
                is NetworkResult.Loading -> { setViewInvisible() }
            }
        }
    }

    private fun setViewVisible() {
        with(binding) {
            textviewRandomEmoji.visibility = View.VISIBLE
            textviewRandomNameEmoji.visibility = View.VISIBLE
            buttonRandomEmoji.visibility = View.VISIBLE
            progressRandomEmoji.visibility = View.GONE
        }
    }

    private fun setViewInvisible() {
        with(binding) {
            textviewRandomEmoji.visibility = View.GONE
            textviewRandomNameEmoji.visibility = View.GONE
            buttonRandomEmoji.visibility = View.GONE
            progressRandomEmoji.visibility = View.VISIBLE
        }
    }

    private fun getRandom() {
        viewModel.getRandomEmoji()
        setViewInvisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
