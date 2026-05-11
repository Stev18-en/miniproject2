package com.stevenmarchy0013.simukmin.screen

import androidx.lifecycle.ViewModel
import com.stevenmarchy0013.simukmin.model.Setoran

class MainViewModel : ViewModel() {

    val data = listOf(

        Setoran(
            1,
            "Ahmad Fauzan",
            "Al-Mulk",
            "1-10",
            "2026-05-12",
            "Hafalan lancar dan tajwid sudah sangat baik."
        ),

        Setoran(
            2,
            "Muhammad Rizki",
            "An-Naba",
            "1-15",
            "2026-05-13",
            "Masih perlu perbaikan pada makhraj huruf qaf dan kho."
        ),

        Setoran(
            3,
            "Aisyah Putri",
            "Al-Waqiah",
            "1-20",
            "2026-05-14",
            "Sudah lancar, hanya beberapa ayat masih kurang panjang pendek."
        ),

        Setoran(
            4,
            "Nabila Zahra",
            "Yasin",
            "1-12",
            "2026-05-15",
            "Bacaan cukup baik dan percaya diri saat menyetor hafalan."
        ),

        Setoran(
            5,
            "Fajar Ramadhan",
            "Ar-Rahman",
            "1-18",
            "2026-05-16",
            "Masih sering lupa pada ayat tertentu dan perlu murojaah."
        ),

        Setoran(
            6,
            "Siti Khadijah",
            "Al-Kahfi",
            "1-10",
            "2026-05-17",
            "Hafalan bagus, suara jelas, dan tajwid sangat rapi."
        )

    )
}