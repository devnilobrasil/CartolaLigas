package com.example.admcartolaligas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.admcartolaligas.R
import com.example.admcartolaligas.databinding.FragmentHomeBinding
import com.example.admcartolaligas.helper.showBottomSheet
import com.example.admcartolaligas.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        configTabLayout()
        initClicks()
    }

    private fun configTabLayout() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        adapter.addFragment(AdicionarFragment(), R.string.txt_Jogadores)
        adapter.addFragment(InformativoFragment(), R.string.txt_tabLayout_informacoes)

        binding.viewPager.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(
            binding.tabs, binding.viewPager
        ) { tab, position ->
            tab.text = getString(adapter.getTitle(position))
        }.attach()

    }

    // Método para todas as funções de clicks
    private fun initClicks() {
        binding.ibLogout.setOnClickListener {
            showBottomSheet(
                message = R.string.text_close_app_bottom_sheet,
                titleButton = R.string.bs_button_confirm,
                onClick = {logoutUser()}
            )
        }
    }

    private fun logoutUser() {
        auth.signOut()
        findNavController().navigate(R.id.action_homeFragment_to_authentication)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}