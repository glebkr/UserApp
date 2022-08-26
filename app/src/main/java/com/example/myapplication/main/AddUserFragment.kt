package com.example.myapplication.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FFragmentAddUserBinding
import com.example.myapplication.databinding.FFragmentUserBinding
import com.example.myapplication.room.UserEntity
import com.example.myapplication.viewModel.ViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddUserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var userViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userViewModel = ViewModelProvider(this)[ViewModel::class.java]
        val binding = FFragmentAddUserBinding.inflate(layoutInflater)
        val view = binding.root
        binding.addUserBtn.setOnClickListener {
            val userSurname = binding.addSurname.text.trim().toString()
            val userName = binding.addName.text.trim().toString()
            val userSecondName = binding.addSecondName.text.trim().toString()
            val userAge = binding.addAge.text.trim().toString()
            val userHeight = binding.addHeight.text.trim().toString()
            checkFieldsNullable(userSurname, userName, userSecondName, userAge, userHeight)
        }
        return view
    }

    private fun checkFieldsNullable(surname: String, name: String, secondName: String, age: String, height: String) {
        if (surname.isNotEmpty() && name.isNotEmpty() && secondName.isNotEmpty() && age.isNotEmpty() && height.isNotEmpty()) {
            val user = UserEntity(surname, name, secondName, age.toInt(), height.toDouble())
            addUserToDB(user)
            Toast.makeText(requireContext(), "Пользователь добавлен!!", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
    }

    private fun addUserToDB(user: UserEntity) {
        userViewModel.addUser(user)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}