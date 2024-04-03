package uz.gita.quizapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.quizapp.R
import uz.gita.quizapp.data.model.QuizData
import uz.gita.quizapp.databinding.ItemTest2Binding

class QuizAdapter : ListAdapter<QuizData, QuizAdapter.ViewHolder>(QuizDiffUtil) {
    private var listener: ((QuizData) -> Unit)? = null
    fun setListener(block: (QuizData) -> Unit) {
        listener = block
    }

    object QuizDiffUtil : DiffUtil.ItemCallback<QuizData>() {
        override fun areItemsTheSame(oldItem: QuizData, newItem: QuizData) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: QuizData, newItem: QuizData) = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemTest2Binding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener?.invoke(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.root.startAnimation(AnimationUtils.loadAnimation(binding.root.context, R.anim.anim_quiz))
            getItem(adapterPosition).apply {
                binding.tvTitle.text = this.title
                binding.tvDescription.text = this.description
                binding.time.text = "${this.time} min"
                binding.image.setImageResource(this.imgResID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTest2Binding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
}