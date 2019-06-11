package com.home.lhduy.maboassistant

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.speech.RecognizerIntent
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_phone.*
import java.lang.Exception
import java.util.*


class PhoneActivity : AppCompatActivity() {

    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        checkPermission()
        voiceBtn.setOnClickListener{
            speak()
        }
    }
    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi speak something")
        try {
            startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                if(resultCode == Activity.RESULT_OK && null != data){
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    tvCommand.text = result[0]
                    val dataResult = result[0]
                    if(dataResult.equals("make a call")){
                        startActivity(Intent(Intent.ACTION_DIAL))
                    }
                    else if (dataResult.equals("send message")){

                    }
                    else if (dataResult.equals("Open YouTube")){
                        val youtubeIntent: Intent = Uri.parse("https://www.youtube.com").let {webpage ->
                            Intent(Intent.ACTION_VIEW, webpage)
                        }
                        startActivity(youtubeIntent)
                    }
                    else if (dataResult.equals("Play music")){
                        val mp3Intent: Intent = Uri.parse("https://www.zingmp3.vn").let {webpage ->
                            Intent(Intent.ACTION_VIEW, webpage)
                        }
                        startActivity(mp3Intent)
                    }
                }
            }
        }
    }

    private fun checkPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)){
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + packageName))
                startActivity(intent)
                finish()
            }
        }
    }
}
