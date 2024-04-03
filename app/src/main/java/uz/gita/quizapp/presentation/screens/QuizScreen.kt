package uz.gita.quizapp.presentation.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.quizapp.R
import uz.gita.quizapp.data.model.QuestionModel
import uz.gita.quizapp.databinding.ScreenQuizBinding
import uz.gita.quizapp.presentation.dialogs.FinishDialog
import uz.gita.quizapp.presentation.viewmodels.QuizViewModel
import uz.gita.quizapp.presentation.viewmodels.impl.QuizViewModelImpl
import uz.gita.quizapp.utils.logger

@AndroidEntryPoint
class QuizScreen : Fragment(R.layout.screen_quiz) {
    private var _binding: ScreenQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by viewModels<QuizViewModelImpl>()
    private lateinit var timer: CountDownTimer
    private var currentMillis: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ScreenQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            backPressed()
        }
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loadQuestion()
        setObservers()

        binding.apply {
            binding.radioGroupVariants.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId != -1) binding.btnNext.isEnabled = true
            }

            btnNext.setOnClickListener {
                var index = -1
                when (radioGroupVariants.checkedRadioButtonId) {
                    variant0.id -> index = 0
                    variant1.id -> index = 1
                    variant2.id -> index = 2
                    variant3.id -> index = 3
                }
                viewModel.checkAnswerByIndex(index)
                radioGroupVariants.clearCheck()
            }

            btnBack.setOnClickListener {
                backPressed()
            }
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun setObservers() {
        viewModel.questionLoaderLiveData.observe(this@QuizScreen, questionLoaderObserver)
        viewModel.questionCountLiveData.observe(this@QuizScreen, questionCountObserver)
        viewModel.progressLiveData.observe(this@QuizScreen, progressObserver)
        viewModel.openFinishScreen.observe(this@QuizScreen, openFinishScreenObserver)
        viewModel.timerLiveData.observe(this@QuizScreen, timerObserver)
        viewModel.clearChecks.observe(this@QuizScreen, clearChecksObserver)
        viewModel.nextButtonState.observe(this@QuizScreen, nextButtonStateObserver)
    }

    private val questionLoaderObserver = Observer<QuestionModel> {
        binding.apply {
            this.tvQuestion.text = it.question
            this.radioGroupVariants.forEachIndexed { index, btn ->
                try {
                    (btn as RadioButton).text = it.options[index]
                } catch (e: ArrayIndexOutOfBoundsException) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private val questionCountObserver = Observer<String> {
        binding.tvQuestionCount.text = "Question $it"
    }

    private val progressObserver = Observer<Int> {
        binding.progressIndicator.progress = it
    }

    private val openFinishScreenObserver = Observer<Int> {
        findNavController().navigate(QuizScreenDirections.actionQuizScreenToResultScreen())
    }

    private val nextButtonStateObserver = Observer<Boolean> {
        binding.btnNext.isEnabled = it
    }

    private fun startTimer(it: Long) {
        timer = object : CountDownTimer(it, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentMillis = millisUntilFinished

                val secondsRemaining = millisUntilFinished / 1000
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.tvTimer.text = "00:00"
                viewModel.onTimeExpired()
            }
        }.start()
    }

    private val timerObserver = Observer<Int> {
        startTimer(it * 60 * 1000L)
    }
    private val clearChecksObserver = Observer<Unit> {
        binding.radioGroupVariants.clearCheck()
    }

    private fun backPressed() = run {
        timer.cancel()

        val dialog = FinishDialog()
        dialog.setNoListener {
            dialog.dismiss()
            startTimer(currentMillis)
        }

        dialog.setYesListener {
            dialog.dismiss()
            findNavController().popBackStack()
        }

        dialog.show(parentFragmentManager, "")
    }
}