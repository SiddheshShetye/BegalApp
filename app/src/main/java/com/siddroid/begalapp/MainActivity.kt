package com.siddroid.begalapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siddroid.begal.core.Begal
import com.siddroid.begal.core.BegalConfig
import com.siddroid.begal.core.BegalInit
import com.siddroid.begal.core.MemCacheConfig
import com.siddroid.begal.domain.model.BegalEntityData
import com.siddroid.begal.domain.model.Data
import com.siddroid.begal.domain.model.EntityState
import com.siddroid.begalapp.databinding.ActivityMainBinding
import com.siddroid.begalapp.ui.DogAdapter

class MainActivity : AppCompatActivity() {

    private val adapter = DogAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        BegalInit.init(application, BegalConfig(true, MemCacheConfig(20)))
        initView()
    }

    private val getImages: (BegalEntityData<List<Data>>) -> Unit = {
        if (it.state == EntityState.SUCCESS) {
            it.data?.let { dto -> adapter.setData(dto) }
        }
        binding.btnPrevious.isEnabled = Begal.getCurrentIndex() > 0
    }

    private fun initView() {
        binding.rvDogs.adapter = adapter
        binding.btnPrevious.isEnabled = Begal.getCurrentIndex() > 0
        binding.btnNew.setOnClickListener {
            val count =
                if (binding.edtCount.text.toString().isBlank() || binding.edtCount.text.toString() == "0"
                ) 0 else binding.edtCount.text.toString().toInt()

            binding.edtCount.text.clear()
            if (count == 0) {
                Begal.getImage(getImages)
            } else {
                Begal.getImages(count, getImages)
            }
        }
        binding.btnNext.setOnClickListener {
            Begal.getNexImage(getImages)
        }
        binding.btnPrevious.setOnClickListener {
            Begal.getPreviousImage(getImages)
        }
        binding.btnNew.performClick()
    }
}