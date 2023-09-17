package com.sesac.agentmanage.utils

import java.io.File

object FileManage {
    fun checkFilePath(filePath: String) = File(filePath).exists() && !File(filePath).isDirectory
}