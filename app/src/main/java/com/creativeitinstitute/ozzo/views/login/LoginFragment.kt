package com.creativeitinstitute.ozzo.views.login

import android.widget.Toast
import com.creativeitinstitute.ozzo.base.BaseFragment
import com.creativeitinstitute.ozzo.databinding.FragmentLoginBinding
import com.creativeitinstitute.ozzo.isEmpty

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    override fun setListener() {
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

    override fun allObserver() {

    }


}