package com.app.slideusers.presentation.ui.feature_user_list.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.slideusers.R
import com.app.slideusers.databinding.UserListFragmentBinding
import com.app.slideusers.presentation.ui.feature_user_list.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var binding: UserListFragmentBinding

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
    }

    private fun subscribeObservers() {
        viewModel.responseUserList.observe(viewLifecycleOwner, { result ->
            if (result.isLoading) {

            } else if (result.userList.isNotEmpty()) {

            } else {

            }
        })
    }

}