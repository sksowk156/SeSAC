package com.sesac.agentmanage.data

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idolgroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SerializeData private constructor() {
    companion object {
        private var instance: SerializeData? = null
        fun getSerializeData(): SerializeData =
            instance ?: SerializeData().also { instance = it }
    }

    // 아이돌 그룹
    fun serializeIdolgroupList(list: MutableList<Idolgroup>) = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./agentFiles/idolgroupList.ser")).use {
                it.writeObject(list)
                it.flush()
            }
            "아이돌 그룹 리스트 직렬화 성공!"
        }
        println(message)
    }

    fun DeserializeIdolgroupList() = runBlocking {
        val idolgroupList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./agentFiles/idolgroupList.ser")).use {
                it.readObject() as MutableList<Idolgroup>
            }
        }
        idolgroupList
    }

    // 행사
    fun serializeEventList(list: MutableList<Event>) = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./agentFiles/eventList.ser")).use {
                it.writeObject(list)
                it.flush()
            }
            "행사 리스트 직렬화 성공!"
        }
        println(message)
    }

    fun DeserializeEventList() = runBlocking {
        val eventList = withContext(Dispatchers.IO) {
            ObjectInputStream(FileInputStream("./agentFiles/eventList.ser")).use {
                it.readObject() as MutableList<Event>
            }
        }
        eventList
    }


    // 회사
    fun serializeCompanyList(list: MutableList<Company>) = runBlocking {
        val message = withContext(Dispatchers.IO) {
            ObjectOutputStream(FileOutputStream("./agentFiles/companyList.ser")).use {
                it.writeObject(list)
                it.flush()
            }
            "회사 리스트 직렬화 성공!"
        }
        println(message)
    }

    fun DeserializeCompanyList() = runBlocking {
        val companyList = withContext(Dispatchers.IO) {

            ObjectInputStream(FileInputStream("./agentFiles/companyList.ser")).use {
                it.readObject() as MutableList<Company>
            }
        }
        companyList
    }
}