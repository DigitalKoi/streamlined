package io.github.reactivecircus.streamlined.home

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import io.github.reactivecircus.streamlined.design.enableDefaultCornerRadius
import io.github.reactivecircus.streamlined.domain.model.Story
import io.github.reactivecircus.streamlined.home.databinding.ItemEmptyPlaceholderBinding
import io.github.reactivecircus.streamlined.home.databinding.ItemMainStoryBinding
import io.github.reactivecircus.streamlined.home.databinding.ItemReadMoreHeadlinesBinding
import io.github.reactivecircus.streamlined.home.databinding.ItemSectionHeaderBinding
import io.github.reactivecircus.streamlined.home.databinding.ItemStoryBinding
import io.github.reactivecircus.streamlined.ui.util.timeAgo
import reactivecircus.blueprint.ui.extension.setPrecomputedTextFuture
import io.github.reactivecircus.streamlined.design.R as ThemeResource

internal sealed class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

internal class MainStoryViewHolder(
    private val binding: ItemMainStoryBinding
) : FeedViewHolder(binding.root) {
    fun bind(
        story: Story,
        isLastItem: Boolean,
        actionListener: FeedsListAdapter.ActionListener
    ) {
        binding.storyImageView.run {
            enableDefaultCornerRadius()
            if (story.imageUrl != null) {
                isVisible = true
                load(story.imageUrl)
            } else {
                isVisible = false
                load(story.imageUrl)
            }
        }
        binding.storySourceTextView.setPrecomputedTextFuture(story.source)
        binding.storyTitleTextView.setPrecomputedTextFuture(story.title)
        binding.publishedTimeTextView.setPrecomputedTextFuture(
            story.publishedTime.timeAgo(PUBLISHED_TIME_DATE_PATTERN)
        )
        binding.bookmarkButton.run {
            setIconResource(ThemeResource.drawable.ic_twotone_bookmark_border_24)
            setOnClickListener {
                actionListener.bookmarkToggled(story)
            }
        }
        binding.moreButton.setOnClickListener {
            actionListener.moreButtonClicked(story)
        }
        binding.divider.isVisible = !isLastItem
        itemView.setOnClickListener { actionListener.storyClicked(story) }
    }
}

internal class StoryViewHolder(
    private val binding: ItemStoryBinding
) : FeedViewHolder(binding.root) {
    fun bind(
        story: Story,
        isLastItem: Boolean,
        actionListener: FeedsListAdapter.ActionListener
    ) {
        binding.storyImageView.run {
            enableDefaultCornerRadius()
            if (story.imageUrl != null) {
                isVisible = true
                load(story.imageUrl)
            } else {
                isVisible = false
                load(story.imageUrl)
            }
        }
        binding.storySourceTextView.setPrecomputedTextFuture(story.source)
        binding.storyTitleTextView.setPrecomputedTextFuture(story.title)
        binding.publishedTimeTextView.setPrecomputedTextFuture(
            story.publishedTime.timeAgo(PUBLISHED_TIME_DATE_PATTERN)
        )
        binding.bookmarkButton.setIconResource(ThemeResource.drawable.ic_twotone_bookmark_border_24)
        binding.bookmarkButton.setOnClickListener {
            actionListener.bookmarkToggled(story)
        }
        binding.moreButton.setOnClickListener {
            actionListener.moreButtonClicked(story)
        }
        binding.divider.isVisible = !isLastItem
        itemView.setOnClickListener { actionListener.storyClicked(story) }
    }
}

internal class SectionHeaderViewHolder(
    private val binding: ItemSectionHeaderBinding
) : FeedViewHolder(binding.root) {
    fun bind(feedType: FeedType) {
        binding.titleTextView.setPrecomputedTextFuture(
            if (feedType is FeedType.TopHeadlines) {
                itemView.context.getString(R.string.feed_type_top_headlines)
            } else {
                itemView.context.getString(R.string.feed_type_for_you)
            }
        )
    }
}

internal class ReadMoreHeadlinesViewHolder(
    binding: ItemReadMoreHeadlinesBinding
) : FeedViewHolder(binding.root) {
    fun bind(actionListener: FeedsListAdapter.ActionListener) {
        itemView.setOnClickListener { actionListener.readMoreHeadlinesButtonClicked() }
    }
}

internal class EmptyPlaceholderViewHolder(
    private val binding: ItemEmptyPlaceholderBinding
) : FeedViewHolder(binding.root) {
    fun bind(feedType: FeedType) {
        binding.noStoriesTextView.setPrecomputedTextFuture(
            if (feedType is FeedType.TopHeadlines) {
                itemView.context.getString(R.string.no_headline_stories_found)
            } else {
                itemView.context.getString(R.string.no_personalized_stories_found)
            }
        )
        binding.divider.isVisible = feedType is FeedType.TopHeadlines
    }
}

internal const val PUBLISHED_TIME_DATE_PATTERN = "MMM dd, YYYY"
