package com.example.hyperlocalcommunity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnnouncementsFragment : Fragment(), AnnouncementAdapter.OnItemClickListener {

    private lateinit var recyclerViewAnnouncements: RecyclerView
    private lateinit var announcementAdapter: AnnouncementAdapter
    private var announcementList: MutableList<Announcement> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcements, container, false)

        recyclerViewAnnouncements = view.findViewById(R.id.recyclerViewAnnouncementsFragment)
        recyclerViewAnnouncements.layoutManager = LinearLayoutManager(context)

        loadSampleAnnouncements()

        announcementAdapter = AnnouncementAdapter(announcementList, this)
        recyclerViewAnnouncements.adapter = announcementAdapter

        return view
    }

    private fun loadSampleAnnouncements() {
        announcementList.clear()
        announcementList.add(Announcement("Community Cleanup Day", "Join us this Saturday to help clean up the park!", "May 25, 2025 - 9:00 AM", "announcement"))
        announcementList.add(Announcement("Local Farmers Market", "Fresh produce, local crafts, and more. Every Sunday.", "May 26, 2025 - 10:00 AM to 2:00 PM", "announcement"))
        announcementList.add(Announcement("Town Hall Meeting", "Discussion on new community initiatives.", "May 28, 2025 - 7:00 PM", "announcement"))
    }

    override fun onItemClick(announcement: Announcement) {
        Toast.makeText(context, "Announcement: ${announcement.title}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnnouncementsFragment()
    }
}