package com.creativeitinstitute.ozzo.views.register

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.ozzo.R
import com.creativeitinstitute.ozzo.base.BaseFragment
import com.creativeitinstitute.ozzo.core.DataState
import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.databinding.FragmentRegisterBinding
import com.creativeitinstitute.ozzo.isEmpty
import com.creativeitinstitute.ozzo.views.dashboard.seller.SellerDashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            btLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
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
                    startActivity(Intent(requireContext(), SellerDashboard::class.java))
                    requireActivity().finish()

                }
            }
        }

    }

}