package uz.gita.quizapp.presentation.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.quizapp.R
import uz.gita.quizapp.databinding.DialogFinishBinding


class FinishDialog : DialogFragment(R.layout.dialog_finish) {
    private val binding by viewBinding(DialogFinishBinding::bind)

    private var yesListener: (() -> Unit)? = null
    private var noListener: (() -> Unit)? = null

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return dialog
    }

    fun setYesListener(block: () -> Unit) {
        yesListener = block
    }

    fun setNoListener(block: () -> Unit) {
        noListener = block
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnYes.setOnClickListener {
            yesListener?.invoke()
        }
        binding.btnNo.setOnClickListener {
            noListener?.invoke()
        }
    }
}