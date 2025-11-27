package com.creativeitinstitute.ozzo.views.dashboard.seller.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.creativeitinstitute.ozzo.R
import com.creativeitinstitute.ozzo.base.BaseFragment
import com.creativeitinstitute.ozzo.core.DataState
import com.creativeitinstitute.ozzo.data.models.Product
import com.creativeitinstitute.ozzo.databinding.FragmentMyProductBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProductFragment : BaseFragment<FragmentMyProductBinding>(FragmentMyProductBinding::inflate) {
    private val viewModel: ProductViewModel by viewModels ()


    override fun setListener() {

        FirebaseAuth.getInstance().currentUser?.let {
            viewModel.getProductByID(it.uid)
        }

    }

    override fun allObserver() {

        viewModel.productResponse.observe(viewLifecycleOwner){
            when(it) {
                is DataState.Error -> {
                    loading.dismiss()
                }
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success-> {
                    it.data?.let { it1->
                        setDataToRV(it1)
                    }
                    loading.dismiss()
                }
            }
        }

    }

    private fun setDataToRV (productList: List<Product>){

        binding.rvSeller.adapter = SellerProductAdapter(productList)
    }


}