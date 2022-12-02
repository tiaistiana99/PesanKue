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
import com.example.a203110041_tiaistiana_p11.databinding.FragmentStartBinding
import com.example.a203110041_tiaistiana_p11.model.OrderViewModel

/**
 * Ini adalah layar pertama aplikasi Cupcake. Pengguna dapat memilih berapa banyak cupcake yang akan dipesan.
 */
class StartFragment : Fragment() {

    // Kita melakukan penyimpanan instance objek yang sesuai dengan tata letak fragment_pickup.xml
    // Properti ini bukan nol antara callback siklus hidup onCreateView() dan onDestroyView(),
    // saat proses loading tampilan dilampirkan ke fragmen.
    private var binding: FragmentStartBinding? = null

    // Kita menggunakan delegasi properti Kotlin 'by activityViewModels()' dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }
    /**
     * Mulai pesanan dengan jumlah cupcakes yang diinginkan dan arahkan ke layar berikutnya.
     */
    fun orderCupcake(quantity: Int) {
        // Kita memperrbarui model tampilan dengan kuantitas
        sharedViewModel.setQuantity(quantity)

        // Jika belum ada ragam yang disetel dalam model tampilan, pilih vanilla sebagai ragam default
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Digunkan untuk memilih rasa kue
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
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