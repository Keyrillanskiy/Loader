package com.github.keyrillanskiy.loader.presentation.screens.loader

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.keyrillanskiy.loader.R
import com.github.keyrillanskiy.loader.databinding.ItemThemeBinding
import com.github.keyrillanskiy.loader.databinding.ItemThemeTitleBinding
import com.github.keyrillanskiy.loader.domain.models.ThemesModel

class LoaderAdapter : ListAdapter<ThemesModel.ThemesDataModel, LoaderViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<ThemesModel.ThemesDataModel>() {
        override fun areItemsTheSame(
            oldItem: ThemesModel.ThemesDataModel,
            newItem: ThemesModel.ThemesDataModel
        ): Boolean = oldItem.title == newItem.title && oldItem.coursesCount == newItem.coursesCount

        override fun areContentsTheSame(
            oldItem: ThemesModel.ThemesDataModel,
            newItem: ThemesModel.ThemesDataModel
        ): Boolean = oldItem.title == newItem.title && oldItem.coursesCount == newItem.coursesCount
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> LoaderViewHolder.ThemeItemViewType.TITLE.ordinal
            else -> LoaderViewHolder.ThemeItemViewType.ITEM.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoaderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LoaderViewHolder.ThemeItemViewType.TITLE.ordinal -> LoaderViewHolder.ThemeTitleViewHolder(
                binding = ItemThemeTitleBinding.inflate(layoutInflater)
            )
            LoaderViewHolder.ThemeItemViewType.ITEM.ordinal -> LoaderViewHolder.ThemeItemViewHolder(
                binding = ItemThemeBinding.inflate(layoutInflater)
            )
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, position: Int) {
        when (holder) {
            is LoaderViewHolder.ThemeTitleViewHolder -> holder.bind()
            is LoaderViewHolder.ThemeItemViewHolder -> holder.bind(getItem(position))
        }
    }

}

sealed class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    enum class ThemeItemViewType {
        TITLE, ITEM
    }

    class ThemeTitleViewHolder(private val binding: ItemThemeTitleBinding) : LoaderViewHolder(binding.root) {
        fun bind() {
            val string = binding.root.context.getString(R.string.learn_actual_themes)
            val coloredString = SpannableString(string)
            val startColorPosition = string.indexOfFirst { it == ' ' }
            coloredString.setSpan(
                ForegroundColorSpan(Color.BLUE),
                startColorPosition,
                string.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.themesTitleTextView.text = coloredString
        }
    }

    class ThemeItemViewHolder(private val binding: ItemThemeBinding) : LoaderViewHolder(binding.root) {
        fun bind(item: ThemesModel.ThemesDataModel) {
            binding.themeTitleTextView.text = item.title
            val coursesCountString = binding.root.context.resources.getQuantityString(
                R.plurals.courses_count,
                item.coursesCount,
                item.coursesCount
            )
            binding.themeCoursesCountTextView.text = coursesCountString
        }
    }
}
