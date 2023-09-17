package com.sesac.agentmanage.data

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.utils.FileManage.checkFilePath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.*

class SerializeData private constructor() {
    companion object {
        private var instance: SerializeData? = null
        fun getSerializeData(): SerializeData =
            instance ?: SerializeData().also { instance = it }
    }

    // 아이돌 그룹
    suspend fun serializeIdolgroupList(list: MutableList<Idolgroup>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/idolgroupList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "아이돌 그룹 리스트 직렬화 성공!"
    }


    suspend fun DeserializeIdolgroupList() = withContext(Dispatchers.IO) {
        if (checkFilePath("./agentFiles/idolgroupList.ser")) {
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

    // 행사
    suspend fun serializeEventList(list: MutableList<Event>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/eventList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "행사 리스트 직렬화 성공!"
    }

    suspend fun DeserializeEventList() = withContext(Dispatchers.IO) {
        if (checkFilePath("./agentFiles/eventList.ser")) {
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

    // 회사
    suspend fun serializeCompanyList(list: MutableList<Company>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/companyList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "회사 리스트 직렬화 성공!"
    }

    suspend fun DeserializeCompanyList() = withContext(Dispatchers.IO) {
        if (checkFilePath("./agentFiles/companyList.ser")) {
            ObjectInputStream(FileInputStream("./agentFiles/companyList.ser")).use {
                it.readObject() as MutableList<Company>
            }
        } else {
            ObjectOutputStream(FileOutputStream("./agentFiles/companyList.ser")).use {
                it.writeObject(mutableListOf<Company>())
                it.flush()
            }
            ObjectInputStream(FileInputStream("./agentFiles/companyList.ser")).use {
                it.readObject() as MutableList<Company>
            }
        }
    }


}