package com.gunder.myreadwritefile.helper

import android.content.Context
import com.gunder.myreadwritefile.model.FileModel

internal object FileHelper {
    fun writeToFile(fileModel: FileModel, context: Context){
        context.openFileOutput(fileModel.fileName, Context.MODE_PRIVATE).use {
            it.write(fileModel.data?.toByteArray())
        }
    }
    fun readFromFile(context: Context, FileName: String): FileModel{
        val fileModel = FileModel()
    }
}