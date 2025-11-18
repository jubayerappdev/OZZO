package com.creativeitinstitute.ozzo.views.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.creativeitinstitute.ozzo.R
import com.creativeitinstitute.ozzo.base.BaseFragment
import com.creativeitinstitute.ozzo.core.DataState
import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.databinding.FragmentLoginBinding
import com.creativeitinstitute.ozzo.databinding.FragmentRegisterBinding
import com.creativeitinstitute.ozzo.isEmpty

class RegisterFragment : BaseFragment<FragmentRegisterBinding>
    (FragmentRegisterBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels ()

    override fun setListener() {

        with(binding){
            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etName.isEmpty() &&!etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done...", Toast.LENGTH_SHORT).show()

                    val user= UserRegistration(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        "Seller",
                        ""
                    )

                    viewModel.userRegistration(user)


                }
            }
        }

    }


    //OOAD -> Object Oriented Analysis Design (Code Design)
    override fun allObserver() {
        registrationResponse()

    }

    private fun registrationResponse() {

        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it) {
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "created User : ${it.data}", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
    //oihdgdsvuvgeskgbeagv


}