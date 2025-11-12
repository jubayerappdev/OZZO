package com.creativeitinstitute.ozzo.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.creativeitinstitute.ozzo.R
import com.creativeitinstitute.ozzo.databinding.FragmentLoginBinding
import com.creativeitinstitute.ozzo.isEmpty

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setListener()
        return binding.root
    }

    private fun setListener() {

        with(binding){
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done...", Toast.LENGTH_LONG).show()
                }
            }
        }

    }


}