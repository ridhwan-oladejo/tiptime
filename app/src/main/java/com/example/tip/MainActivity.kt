 package com.example.tip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tip.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener{calculateTip()}
        binding.costOfServiceShell.setOnKeyListener{view,keycode,_ -> handleKeyEvent(view,keycode)}
    }


     private fun calculateTip() {
         var sol = ""
         val tipValue = binding.costOfService.text.toString().toDoubleOrNull()
         val tipPercent = when(binding.tipOptions.checkedRadioButtonId){
             R.id.amazing ->  0.2
             R.id.good -> 0.18
             else -> 0.15
         }
         if (tipValue == null) {
             binding.finalTipValue.text = "0"
             return
         }
         var finalValue = tipValue * tipPercent

         if (binding.roundUp.isChecked){
             finalValue = kotlin.math.ceil(finalValue)
         }
         sol = finalValue.toString()
         binding.finalTipValue.text = sol
     }
     private fun handleKeyEvent(view:View, keyCode : Int): Boolean{
         if(keyCode == KeyEvent.KEYCODE_ENTER){
             val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
             inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
             return true
         }
         return false
     }
}