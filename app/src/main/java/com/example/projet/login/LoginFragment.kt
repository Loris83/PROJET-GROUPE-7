package com.example.projet.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projet.DatabaseHelper
import com.example.projet.MainActivity
import com.example.projet.R
import com.example.projet.databinding.FragmentLoginBinding
import com.example.projet.user.UserDataModel
import com.example.projet.user.UserModel
import com.example.projet.user.UserSession
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*


class LoginFragment : Fragment() {
   private lateinit var binding : FragmentLoginBinding

    private lateinit var inputUserName: String
    private lateinit var inputPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        loggingFunction()
        registerFunction()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun loggingFunction (){
        binding.buttonLogIn.setOnClickListener{

            //Getting the inputs from their fields
            inputUserName = binding.editTextUserName.text.toString().trim()
            inputPassword = binding.editTextPassword.text.toString().trim()
            // Checking the validity of inputs

            if (checkIfNotEmpty(inputUserName, inputPassword))
            { Log.d("", "CHECK IF EMPTY PASSED "+inputPassword+" "+inputUserName)
                if (checkLength(inputUserName, inputPassword))
                { Log.d("", "CHECK LENGTH PASSED"+inputPassword+" "+inputUserName)
                    if (checkFormatType(inputUserName, inputPassword))
                    { Log.d("", "CHECK FORMAT PASSED, YOU ARE GOOD TO GO"+inputPassword+" "+inputUserName)
                        //Toast.makeText(this.context, "you are good to go", Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this.context, R.string.login_no_in_format, Toast.LENGTH_SHORT).show()
                        //type format
                    }
                }else{
                    Toast.makeText(this.context, R.string.login_to_short, Toast.LENGTH_SHORT).show()
                }
            }else
            {
                Toast.makeText(this.context , R.string.login_empty, Toast.LENGTH_SHORT ).show()
            }
            //getUser(inputUserName,inputPassword)
            UserModel.findUser(inputUserName, inputPassword,{
                user ->
                if(user?.password == inputPassword) {
                    Log.d("dataBase","connected")
                    Toast.makeText(binding.root.context, R.string.login_successful, Toast.LENGTH_SHORT).show()
                    UserSession.login(user)
                    MainActivity.setupMenu()
                    findNavController().navigate(R.id.nav_home)

                }
                else{
                    Toast.makeText(binding.root.context, R.string.login_no_successful, Toast.LENGTH_SHORT).show()
                }
            },{})
        }
    }

    private fun registerFunction () {
        binding.buttonRegister.setOnClickListener {
            //Getting the inputs from their fields
            inputUserName = binding.editTextUserName.text.toString().trim()
            inputPassword = binding.editTextPassword.text.toString().trim()
            // Checking the validity of inputs

            if (checkIfNotEmpty(inputUserName, inputPassword)) {
                Log.d("", "CHECK IF EMPTY PASSED " + inputPassword + " " + inputUserName)
                if (checkLength(inputUserName, inputPassword)) {
                    Log.d("", "CHECK LENGTH PASSED" + inputPassword + " " + inputUserName)
                    if (checkFormatType(inputUserName, inputPassword)) {
                        Log.d(
                            "",
                            "CHECK FORMAT PASSED, YOU ARE GOOD TO GO" + inputPassword + " " + inputUserName
                        )
                    } else {
                        Toast.makeText(
                            this.context,
                            R.string.login_no_in_format,
                            Toast.LENGTH_SHORT
                        ).show()
                        //type format
                    }
                } else {
                    Toast.makeText(this.context, R.string.login_to_short, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this.context, R.string.login_empty, Toast.LENGTH_SHORT).show()
            }

            /*DatabaseHelper.database.getReference("user")
                .orderByChild("username")
                .equalTo(inputUserName).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(!snapshot.exists()) {
                            val user = UserDataModel(inputUserName, "user", inputPassword,false)
                            DatabaseHelper.database.reference.child("user")
                                .child(UUID.randomUUID().toString())
                                .setValue(user)
                        }
                        else{
                            Toast.makeText(binding.root.context, R.string.register_username_already_exists, Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("dataBase", error.toString())
                    }

                })*/
            UserModel.findUser(inputUserName,inputPassword,{
                Toast.makeText(binding.root.context, R.string.register_username_already_exists, Toast.LENGTH_SHORT).show()
            },{
                UserModel.addUser(UserDataModel(inputUserName, "user", inputPassword,false))
            })
        }
    }

    private fun checkFormatType(inputUserName: String, inputPassword: String): Boolean {
        var isAlphanumeric: Boolean = false
        val usernamePattern = "^[a-zA-Z0-9]+$".toRegex()
        if (usernamePattern.matches(inputUserName))
        {
            isAlphanumeric = true
        }
        return  isAlphanumeric
    }

    private fun checkLength(inputUserName: String, inputPassword: String): Boolean {
        var isLongLenght: Boolean = true

        if (inputUserName.length<4 || inputPassword.length<4)
        {
            isLongLenght = false
        }

        return isLongLenght
    }

    private fun checkIfNotEmpty(inputUserName: String, inputPassword: String): Boolean {
        var isEmpty: Boolean = true
        if (inputUserName.isEmpty() || inputPassword.isEmpty())
        {
            isEmpty = false
        }
        return isEmpty
    }

    fun getUser(username: String, password: String) {
        /*
        DatabaseHelper.database.getReference("user")
            .orderByChild("username")
            .equalTo(username)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("dataBase", snapshot.toString())
                    if(snapshot.exists()) {
                        val user = snapshot.children.first().getValue(UserDataModel::class.java)
                        if(user?.password == password) {
                            Log.d("dataBase","connected")
                            Toast.makeText(binding.root.context, R.string.login_successful, Toast.LENGTH_SHORT).show()
                            UserSession.login(user)
                            MainActivity.setupMenu()
                            findNavController().navigate(R.id.nav_home)

                        }
                        else{
                            Toast.makeText(binding.root.context, R.string.login_no_successful, Toast.LENGTH_SHORT).show()
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("dataBase", error.toString())
                }

            })*/
    }
}

