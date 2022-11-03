package com.tbondarenko.testxmlcompose.presentation.screens.listFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.databinding.FragmentListBinding
import com.tbondarenko.testxmlcompose.presentation.models.EmojiUi
import com.tbondarenko.testxmlcompose.presentation.screens.utils.showErrorInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val viewModel by viewModels<ListViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var emojiAdapter: EmojiRecyclerViewAdapter
    private lateinit var errorDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadEmojiList(view)
    }

    private fun loadEmojiList(view: View) {
        getList()
        setRecyclerView(view)
        viewModel.result.observe(this.viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    setViewVisible()
                    val emojiUiList = result.data.toMutableList().map {
                        EmojiUi(
                            name = it.name,
                            category = it.category,
                            group = it.group,
                            html = it.htmlCode.first()
                        )
                    }
                    emojiAdapter.add(emojiUiList)
                }
                is NetworkResult.Error -> {
                    isErrorShow()
                    errorDialog = showErrorInFragment(
                        context = requireContext(),
                        title = result.error,
                        message = result.message,
                        setViewVisibility = { setViewVisible() },
                        tryAgain = { getList() }
                    )
                }
                is NetworkResult.Loading -> { setViewInvisible() }
            }
        }
    }

    private fun setRecyclerView(view: View) {
        recyclerView = binding.recyclerviewEmoji
        emojiAdapter = EmojiRecyclerViewAdapter {
            val action = ListFragmentDirections
                .actionListFragmentToDetailFragment(
                    selectEmoji = it
                )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = emojiAdapter
    }

    private fun setViewVisible() {
        with(binding) {
            recyclerviewEmoji.visibility = View.VISIBLE
            progressListEmoji.visibility = View.GONE
        }
    }

    private fun setViewInvisible() {
        with(binding) {
            recyclerviewEmoji.visibility = View.GONE
            progressListEmoji.visibility = View.VISIBLE
        }
    }

    private fun getList() {
        viewModel.getAllEmoji()
        with(binding) {
            recyclerviewEmoji.visibility = View.GONE
            progressListEmoji.visibility = View.VISIBLE
        }
    }

    private fun isErrorShow() {
        if (::errorDialog.isInitialized) errorDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}