package com.gunder.myreadwritefile.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gunder.myreadwritefile.R
import com.gunder.myreadwritefile.databinding.ActivityMainBinding
import com.gunder.myreadwritefile.helper.FileHelper
import com.gunder.myreadwritefile.model.FileModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //    binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNew.setOnClickListener(this)
        binding.btnOpen.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_new -> NewFile()
            R.id.btn_open -> ShowList()
            R.id.btn_save -> SaveData()
        }

    }

    private fun SaveData() {
        when {
            binding.edtTitle.text.toString().isEmpty() -> Toast.makeText(
                this,
                "isi title terlebih dahulu",
                Toast.LENGTH_SHORT
            )
                .show()
            binding.edtFile.text.toString().isEmpty() -> Toast.makeText(
                this,
                "konten tidak boleh kosong",
                Toast.LENGTH_SHORT
            )
                .show()
            else -> {
                val title = binding.edtTitle.text.toString()
                val text = binding.edtFile.text.toString()
                val fileModel = FileModel()
                fileModel.fileName = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(this, "menyimpan " + fileModel.fileName + "file", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun ShowList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("pilih file")
        builder.setItems(items) { dialog, item -> loadData(items[item].toString()) }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(Title: String) {
        val fileModel = FileHelper.readFromFile(this, Title)
        binding.edtTitle.setText(fileModel.fileName)
        binding.edtFile.setText(fileModel.data)
        Toast.makeText(this, "Loading" + fileModel.fileName + "data", Toast.LENGTH_SHORT).show()
    }

    private fun NewFile() {
        binding.edtTitle.setText("")
        binding.edtFile.setText("")
        Toast.makeText(this, "Clearing file", Toast.LENGTH_SHORT).show()
    }
}