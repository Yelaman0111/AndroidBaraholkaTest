package com.bignerdranch.android.androidtestbaraholka.Fragmets

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bignerdranch.android.androidtestbaraholka.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        signIn.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment())

                .commit()
        }

        button_signUp.setOnClickListener {
            Toast.makeText(context,"Text", Toast.LENGTH_SHORT).show()
            //запрос на сервер на создание нового пользователя
        }


        checkbox_confirm.setOnClickListener {
            if(checkbox_confirm.isChecked){
                button_signUp.isClickable = true
//                button_login.getBackground().setColorFilter(Color.parseColor("#3f51b5"), PorterDuff.Mode.MULTIPLY);
                button_signUp.background.setTint(resources.getColor(R.color.colorButton) )
            }else{
                button_signUp.isClickable = false
                button_signUp.background.setTint(Color.GRAY)
//                button_login.getBackground().setColorFilter(Color.parseColor("#3f51b5"), PorterDuff.Mode.MULTIPLY);
            }
        }




    }
}