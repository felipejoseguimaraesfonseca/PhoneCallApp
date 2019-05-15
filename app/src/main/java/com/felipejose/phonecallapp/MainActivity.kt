package com.felipejose.phonecallapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    var chamar : Button? = null
    var numeroCelular : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chamar = findViewById(R.id.chamar)
        numeroCelular = findViewById(R.id.numeroCelular)
    }
    fun chamarFunc(v: View){
        val intent = Intent(Intent.ACTION_CALL)
        val pNum = numeroCelular!!.text.toString()
        if (TextUtils.isEmpty(numeroCelular!!.text.toString())){
            numeroCelular!!.error = "Sem número"
            return
        }else{
            intent.data = Uri.parse("tel:" +pNum)
        }
        if (ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this@MainActivity, "Permissão para ligar",Toast.LENGTH_SHORT).show()
            requestPermissionsFunc()
        }else{
            startActivity(intent)
        }
    }
    private fun requestPermissionsFunc(){
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.CALL_PHONE),1)
    }
}
