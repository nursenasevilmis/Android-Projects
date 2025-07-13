package com.nursena.gezegenlerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nursena.gezegenlerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // binding değişkenini burada sınıf içinde tanımlıyoruz
    private lateinit var binding: ActivityMainBinding
    val MARS= 0.38
    val VENUS= 0.9
    val JUPITER = 2.53
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding nesnesini oluşturuyoruz
        binding = ActivityMainBinding.inflate(layoutInflater)     // bu bindingler sayesinde designdaki viewleri kolayca kullanabiliriz
        setContentView(binding.root)


        var marsRadio = binding.mars.setOnClickListener {
            var gelenKilo = binding.inputKilo.text.toString()
            var gelenKiloDouble = gelenKilo.toDoubleOrNull()


            if (gelenKiloDouble!=null){
                var sonucDouble= gelenKiloDouble * MARS
                binding.sonucKilo.text = "$sonucDouble"
            }
            else
            {
                binding.sonucKilo.text = "Lütfen tekrar deneyiniz."
            }


        }

        var venusRadio = binding.venus.setOnClickListener {

            var gelenKilo = binding.inputKilo.text.toString()
            var gelenKiloDouble = gelenKilo.toDoubleOrNull()


            if (gelenKiloDouble!=null){
                var sonucDouble= gelenKiloDouble * VENUS
                binding.sonucKilo.text = "$sonucDouble"
            }
            else
            {
                binding.sonucKilo.text = "Lütfen tekrar deneyiniz."
            }
        }

        var jupiterRadio = binding.jupiter.setOnClickListener {
            var gelenKilo = binding.inputKilo.text.toString()
            var gelenKiloDouble = gelenKilo.toDoubleOrNull()


            if (gelenKiloDouble!=null){
                var sonucDouble= gelenKiloDouble * JUPITER
                binding.sonucKilo.text = "$sonucDouble"
            }
            else
            {
                binding.sonucKilo.text = "Lütfen tekrar deneyiniz."
            }
        }









    }
}
