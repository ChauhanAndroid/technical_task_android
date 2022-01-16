package com.app.slideusers.presentation.ui.feature_user_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.slideusers.R
import com.app.slideusers.databinding.UserListFragmentBinding
import com.app.slideusers.presentation.ui.feature_user_list.adapter.UserListAdapter
import com.app.slideusers.presentation.ui.feature_user_list.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration


@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: UserListFragmentBinding
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserList()
        subscribeObservers()
        setUpAdapter()
    }

    private fun subscribeObservers() {
        viewModel.responseUserList.observe(viewLifecycleOwner, { result ->
            if (result.isLoading) {

            } else if (result.userList.isNotEmpty()) {
                if (userListAdapter != null)
                    userListAdapter.setData(result.userList)
            } else {

            }
        })
    }

    private fun setUpAdapter() {
        userListAdapter = UserListAdapter()
        LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        ).apply {
            binding.rvUserList.layoutManager = this
            binding.rvUserList.addItemDecoration(
                DividerItemDecoration(
                    binding.rvUserList.context,
                    orientation
                )
            )
        }
        binding.rvUserList.adapter = userListAdapter
    }

}