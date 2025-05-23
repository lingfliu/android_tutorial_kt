package io.issc.android_tutorial_kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.issc.android_tutorial_kt.BaseFragment
import io.issc.android_tutorial_kt.R
import io.issc.android_tutorial_kt.databinding.FragmentNav1Binding
import io.issc.android_tutorial_kt.databinding.FragmentP1Binding

class FrgNav1 : BaseFragment() {
    lateinit var binding: FragmentNav1Binding

    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button

    override fun onAttach(context: Context) {
        super.onAttach(context)

        signature = "FrgNav1"
        arguments?.let {
            val cnt = it.getInt("cnt")
            Toast.makeText(context, "cnt = $cnt", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNav1Binding.inflate(layoutInflater)

        btn1 = binding.btnNav1
        btn2 = binding.btnNav2
        btn3 = binding.btnNav3

        btn1.setOnClickListener{
            Toast.makeText(context, "Already in Page 1", Toast.LENGTH_SHORT).show()
        }

        btn2.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("key", "Hello from Page 1")
            val controller = findNavController()
            controller.navigate(R.id.action_frgNav1_to_frgNav2, bundle)
        }

        btn3.setOnClickListener{
            val controller = findNavController()
            controller.navigate(R.id.action_frgNav1_to_frgNav3)}

        //提取传递的数据
        arguments?.let {
            val cnt = it.getInt("cnt")
            Toast.makeText(context, "cnt = $cnt", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}