package com.sesac.agentmanage.serialize

import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.utils.FileManage
import com.sesac.agentmanage.utils.SerializeInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class IdolgroupSerialize : SerializeInterface<Idolgroup> {
    override suspend fun serializeDataList(list: MutableList<Idolgroup>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/idolgroupList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "아이돌 그룹 리스트 직렬화 성공!"
    }

    override suspend fun DeserializeDataList() = withContext(Dispatchers.IO) {
        if (FileManage.checkFilePath("./agentFiles/idolgroupList.ser")) {
            ObjectInputStream(FileInputStream("./agentFiles/idolgroupList.ser")).use {
                it.readObject() as MutableList<Idolgroup>
            }
        } else {
            ObjectOutputStream(FileOutputStream("./agentFiles/idolgroupList.ser")).use {
                it.writeObject(mutableListOf<Idolgroup>())
                it.flush()
            }
            ObjectInputStream(FileInputStream("./agentFiles/idolgroupList.ser")).use {
                it.readObject() as MutableList<Idolgroup>
            }
        }
    }
}