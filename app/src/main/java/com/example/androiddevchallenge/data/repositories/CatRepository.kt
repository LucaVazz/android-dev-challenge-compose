/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data.repositories

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.models.Cat

class CatRepository {
    private companion object {
        private val data: List<Cat> = listOf(
            Cat("a", "Ashes", "A truly adorable kitten.", R.drawable.a),
            Cat("b", "Buttons", "A cute kitten which you can fit in your pouch.", R.drawable.b),
            Cat("p", "Pabu", "A striped cat that will sneak into your heart.", R.drawable.p),
            Cat("c", "Cuddles", "A fluffy kitten that's always up to cuddle.", R.drawable.c),
            Cat("i", "Ivy", "They will render your love into adorable purrs.", R.drawable.i),
            Cat(
                "v",
                "Void",
                "If you stare into the void, the void stares back - and it's cute.",
                R.drawable.v
            ),
            Cat("t", "Tiny", "Tiny but mighty.", R.drawable.t),
            Cat("k", "Kegy", "Please get them on the trend page, thanks.", R.drawable.k),
            Cat("s", "Shiro", "Watch out to not loose them on your blanket.", R.drawable.s),
            Cat("w", "Wambo", "A cute kitten", R.drawable.w),
        )
    }

    fun getAll(): List<Cat> {
        return data
    }

    fun getById(id: String): Cat? {
        return data.find { it.id == id }
    }
}
