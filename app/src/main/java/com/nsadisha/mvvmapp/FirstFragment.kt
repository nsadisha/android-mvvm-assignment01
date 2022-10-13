package com.nsadisha.mvvmapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nsadisha.mvvmapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        binding.textField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textview.text =p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.viewModel!!.getData(p0.toString())?.observe(viewLifecycleOwner) {
                    binding.textview.text = it
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.textview.text =p0.toString()
            }
        })
//        binding.textField.addTextChangedListener {
//            binding.viewModel.getData(it.toString())
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}