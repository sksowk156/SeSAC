package com.sesac.agentmanage.serialize

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.utils.FileManage
import com.sesac.agentmanage.utils.SerializeInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class EventSerialize : SerializeInterface<Event> {
    override suspend fun serializeDataList(list: MutableList<Event>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/eventList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "행사 리스트 직렬화 성공!"
    }

    override suspend fun DeserializeDataList() = withContext(Dispatchers.IO) {
        if (FileManage.checkFilePath("./agentFiles/eventList.ser")) {
            ObjectInputStream(FileInputStream("./agentFiles/eventList.ser")).use {
                it.readObject() as MutableList<Event>
            }
        } else {
            ObjectOutputStream(FileOutputStream("./agentFiles/eventList.ser")).use {
                it.writeObject(mutableListOf<Company>())
                it.flush()
            }
            ObjectInputStream(FileInputStream("./agentFiles/eventList.ser")).use {
                it.readObject() as MutableList<Event>
            }
        }
    }
}