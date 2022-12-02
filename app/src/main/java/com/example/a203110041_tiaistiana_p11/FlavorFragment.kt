/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.a203110041_tiaistiana_p11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.a203110041_tiaistiana_p11.databinding.FragmentFlavorBinding
import com.example.a203110041_tiaistiana_p11.model.OrderViewModel

/**
 * [FlavourFragment] memungkinkan pengguna memilih rasa cupcake untuk pesanan.
 */
class FlavorFragment : Fragment() {

    // Digunakan untuk  Mengikat instance objek yang sesuai dengan tata letak yaitu fragment_flavor.xml
    // Nah Properti ini bukan termasuk nol diantara callback siklus hidup onCreateView() dan onDestroyView(),
    // Pada saat terjadi hierarki tampilan ini akan dilampirkan ke dalam fragmen.
    private var binding: FragmentFlavorBinding? = null

    //Kita mengguunakan delegasi properti Kotlin 'by activityViewModels()' dari bagian fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Menentukan Fragment
            lifecycleOwner = viewLifecycleOwner
            // Kita disini menetapkan model tampilan ke properti pada kelas binding
            viewModel = sharedViewModel

            // Tetapkan fragmen
            flavorFragment = this@FlavorFragment
        }
    }

    /**
     * Arahkan ke layar berikutnya untuk memilih tanggal pengambilan.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /**
     * Metode daur hidup fragmen ini dipanggil saat hierarki tampilan terkait dengan fragmen
     * sedang dihapus. Akibatnya, bersihkan objek yang mengikat.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}