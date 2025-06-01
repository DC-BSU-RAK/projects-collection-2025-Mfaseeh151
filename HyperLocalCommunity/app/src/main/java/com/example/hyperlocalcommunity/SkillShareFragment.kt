package com.example.hyperlocalcommunity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.UUID
import java.util.concurrent.TimeUnit

class SkillShareFragment : Fragment(), SkillShareAdapter.OnSkillShareItemClickListener {

    private lateinit var recyclerViewSkillShare: RecyclerView
    private lateinit var skillShareAdapter: SkillShareAdapter
    private var skillShareList: MutableList<SkillSharePost> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_skillshare, container, false)

        recyclerViewSkillShare = view.findViewById(R.id.recyclerViewSkillShareItems)
        recyclerViewSkillShare.layoutManager = LinearLayoutManager(context)

        loadSampleSkillSharePosts()

        // requireContext() is safer here than context, as fragment might not be attached yet
        // when adapter is initialized if done outside onCreateView directly.
        skillShareAdapter = SkillShareAdapter(requireContext(), skillShareList, this)
        recyclerViewSkillShare.adapter = skillShareAdapter

        return view
    }

    private fun loadSampleSkillSharePosts() {
        skillShareList.clear()
        val currentTime = System.currentTimeMillis()

        skillShareList.add(
            SkillSharePost(
                id = UUID.randomUUID().toString(), userId = "user123", userName = "Aisha M.",
                title = "Offering Arabic Calligraphy Basics",
                description = "Happy to teach beginners the basics of Arabic calligraphy. Weekends only.",
                type = PostType.OFFER_SKILL, category = getString(R.string.skillshare_category_crafts),
                timestamp = currentTime - TimeUnit.HOURS.toMillis(2), contactInfo = "DM for schedule"
            )
        )
        skillShareList.add(
            SkillSharePost(
                id = UUID.randomUUID().toString(), userId = "user456", userName = "Omar K.",
                title = "Looking for a Tennis Partner",
                description = "Intermediate player, looking for someone to play with 2-3 times a week.",
                type = PostType.HOBBY_CONNECT, category = getString(R.string.skillshare_category_sports),
                timestamp = currentTime - TimeUnit.DAYS.toMillis(1)
            )
        )
        skillShareList.add(
            SkillSharePost(
                id = UUID.randomUUID().toString(), userId = "user789", userName = "Fatima A.",
                title = "Need help with basic WordPress setup",
                description = "Trying to set up a small blog, need some guidance.",
                type = PostType.REQUEST_SKILL, category = getString(R.string.skillshare_category_tech),
                timestamp = currentTime - TimeUnit.MINUTES.toMillis(30)
            )
        )
    }

    override fun onSkillShareItemClick(post: SkillSharePost) {
        Toast.makeText(context, "SkillShare: ${post.title}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SkillShareFragment()
    }
}