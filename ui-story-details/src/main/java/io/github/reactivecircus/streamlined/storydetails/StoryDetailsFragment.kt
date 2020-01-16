package io.github.reactivecircus.streamlined.storydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.reactivecircus.streamlined.storydetails.databinding.FragmentStoryDetailsBinding
import io.github.reactivecircus.streamlined.ui.base.BaseFragment

class StoryDetailsFragment : BaseFragment<FragmentStoryDetailsBinding>() {

    override fun provideViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStoryDetailsBinding {
        return FragmentStoryDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Story title..."
    }

    companion object {
        const val ARG_STORY_ID = "ARG_STORY_ID"
    }
}