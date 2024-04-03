package uz.gita.quizapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.quizapp.R
import uz.gita.quizapp.data.model.QuestionResultModel
import uz.gita.quizapp.databinding.ItemQuestionResultBinding

class QuizResultAdapter : ListAdapter<QuestionResultModel, QuizResultAdapter.QuestionResultVH>(ResultDiffUtil) {
    inner class QuestionResultVH(
        private val binding: ItemQuestionResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
//            binding.root.startAnimation(AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_quiz))
            binding.apply {
                tvQuestion.text = getItem(adapterPosition).question
                tvChosen.text = getItem(adapterPosition).chosen
                tvCorrect.text = getItem(adapterPosition).correct

                val isCorrect: Boolean
                getItem(adapterPosition).apply { isCorrect = chosen == correct }

                viewColor.setBackgroundColor(
                    if (isCorrect) {
                        ContextCompat.getColor(root.context, R.color.green)
                    } else {
                        ContextCompat.getColor(root.context, R.color.red)
                    }
                )
            }
        }
    }

    object ResultDiffUtil : DiffUtil.ItemCallback<QuestionResultModel>() {
        override fun areItemsTheSame(oldItem: QuestionResultModel, newItem: QuestionResultModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: QuestionResultModel, newItem: QuestionResultModel) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionResultVH(
        ItemQuestionResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: QuestionResultVH, position: Int) = holder.bind()
}