package com.gunder.myreadwritefile.helper

import android.content.Context
import com.gunder.myreadwritefile.model.FileModel

internal object FileHelper {
    fun writeToFile(fileModel: FileModel, context: Context) {
//        open file output digunakan untuk membuka berkas sesuai dengan namanya
        context.openFileOutput(fileModel.fileName, Context.MODE_PRIVATE).use {
            it.write(fileModel.data?.toByteArray())
        }
    }

    fun readFromFile(context: Context, fileName: String): FileModel {
        val fileModel = FileModel()
        fileModel.fileName = fileName
        fileModel.data = context.openFileInput(fileName).bufferedReader().useLines { lines ->
            lines.fold("") { some, text ->
                "$some\n$text"
            }
        }
        return fileModel
    }
}