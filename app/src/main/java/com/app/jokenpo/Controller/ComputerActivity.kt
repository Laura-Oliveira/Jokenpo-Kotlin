package com.app.jokenpo.Controller

import android.content.ContentValues
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.jokenpo.Model.Computer
import com.app.jokenpo.R
import com.app.jokenpo.View.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.String
import kotlin.Throwable
import kotlin.assert


class ComputerActivity : AppCompatActivity()
{
    private lateinit var jsonPlaceholder: APIInterface
    private lateinit var txtComputerName: TextView
    private lateinit var computerName: kotlin.String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        txtComputerName = findViewById(R.id.txt_computerName)
        computerName = txtComputerName.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.toys/api/medieval_name")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceholder = retrofit.create(APIInterface::class.java)
        //getComputer()

    }

   /* fun getComputerName()
    {
        val call: Call<List<Computer>> = jsonPlaceholder.getComputer()
        call.enqueue(object : Callback<List<Computer>>
        {
            override fun onResponse(call: Call<List<Computer>>, response: Response<List<Computer>>)
            {
                if (response.isSuccessful())
                {
                    Toast.makeText(this@ComputerActivity,"Computer Name: $computerName", Toast.LENGTH_SHORT).show()
                    computerName = call.toString()
                }
            }

            override fun onFailure(call: Call<List<Computer>>, t: Throwable)
            {
                Toast.makeText(this@ComputerActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
*/

}