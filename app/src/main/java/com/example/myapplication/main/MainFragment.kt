package com.example.myapplication.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FFragmentMainBinding
import com.example.myapplication.room.UserEntity
import com.example.myapplication.viewModel.ViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    private lateinit var userViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "testTitle", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "testText"
                importance = NotificationManager.IMPORTANCE_DEFAULT
            }
            val notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(requireContext(), SettingsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)
            val bitmap = BitmapFactory.decodeResource(requireContext().resources, R.drawable.ic_add)
            val bitmapLargeIcon = BitmapFactory.decodeResource(requireContext().resources, R.drawable.ic_add)
            val notification = Notification.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle("testTitle")
                .setContentText("testText")
                .setLargeIcon(bitmapLargeIcon)
                //.setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                //.setProgress(10,10,true)
                .setContentIntent(pendingIntent)
            with(NotificationManagerCompat.from(requireContext())) {
                notify(notificationId, notification.build())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FFragmentMainBinding.inflate(layoutInflater)
        val view = binding.root
        userViewModel = ViewModelProvider(this)[ViewModel::class.java]
        binding.button = "Далее"
        binding.nextBtn.setOnClickListener {
            createNotificationChannel()
            sendNotification()
            findNavController().navigate(R.id.userFragment)
        }
        binding.btnAdd = "Добавить чела"
        val user = UserEntity("Курьянов", "Глеб", "Олегович", 19, 192.0)
        binding.addBtn.setOnClickListener {
            userViewModel.addUser(user)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}