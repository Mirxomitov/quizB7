package uz.gita.quizapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.quizapp.R
import uz.gita.quizapp.databinding.ScreenFinishBinding
import uz.gita.quizapp.presentation.adapter.QuizResultAdapter
import uz.gita.quizapp.presentation.viewmodels.QuizResultViewModel
import uz.gita.quizapp.presentation.viewmodels.impl.QuizResultViewModelImpl

@AndroidEntryPoint
class QuizResultScreen : Fragment(R.layout.screen_finish) {
    private var _binding: ScreenFinishBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizResultViewModel by viewModels<QuizResultViewModelImpl>()
    private val adapter = QuizResultAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ScreenFinishBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.resultDataFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.progressIndicatorFlow.onEach {
            binding.circularProgress.progress = it
            binding.tvProgressPercent.text = "$it %"
            binding.tvTitle.text = if (it > 60) "Congrats, You are passed!!!" else "Oops, You are failed!!!"
        }.launchIn(lifecycleScope)

        viewModel.showResults()
    }
}