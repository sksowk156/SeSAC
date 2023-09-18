package com.sesac.agentmanage.serialize

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.utils.FileManage
import com.sesac.agentmanage.utils.SerializeInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class CompanySerialize : SerializeInterface<Company> {
    override suspend fun serializeDataList(list: MutableList<Company>) = withContext(Dispatchers.IO) {
        ObjectOutputStream(FileOutputStream("./agentFiles/companyList.ser")).use {
            it.writeObject(list)
            it.flush()
        }
        "회사 리스트 직렬화 성공!"
    }

    override suspend fun DeserializeDataList() = withContext(Dispatchers.IO) {
        if (FileManage.checkFilePath("./agentFiles/companyList.ser")) {
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