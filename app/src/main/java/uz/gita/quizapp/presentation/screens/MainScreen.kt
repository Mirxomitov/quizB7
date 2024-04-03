package uz.gita.quizapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.quizapp.R
import uz.gita.quizapp.databinding.ScreenMainBinding
import uz.gita.quizapp.presentation.adapter.QuizAdapter
import uz.gita.quizapp.presentation.viewmodels.MainViewModel
import uz.gita.quizapp.presentation.viewmodels.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter = QuizAdapter()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.quizList.adapter = adapter
        binding.quizList.layoutManager = LinearLayoutManager(requireContext())

        adapter.setListener {
            viewModel.openQuizScreen(it)
        }

        viewModel.showQuizDataFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.openQuizScreen.observe(this@MainScreen, openQuizScreenObserver)
        viewModel.showQuizData()
    }

    private val openQuizScreenObserver = Observer<Unit> {
        findNavController().navigate(MainScreenDirections.actionMainScreenToQuizScreen())
    }
}