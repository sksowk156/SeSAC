package com.sesac.agentmanage.manage.company

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.utils.SerializeInterface

class CompanyManage(private val serializedData: SerializeInterface<Company>) {
     lateinit var companyList: MutableList<Company>

    //회사 전체 조회
    suspend fun getCompanyList(): MutableList<Company> {
        companyList = serializedData.DeserializeDataList()
        return companyList
    }

    fun getCompanyListByGroupName(name: String) = companyList.find { it.idolgroup.any { it.name == name } }
    suspend fun deleteCompany(company: Company) { // 삭제
        if (companyList.remove(company)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }

        serializedData.serializeDataList(companyList)
    }

    suspend fun setCompanyList(company: Company) { // 등록
        if (companyList.add(company)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        serializedData.serializeDataList(companyList)
    }

    suspend fun updateCompanyAddIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정, 회사에 아이돌 그룹 추가
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.add(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializedData.serializeDataList(companyList)
    }

    suspend fun updateCompanyRemoveIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.remove(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializedData.serializeDataList(companyList)
    }
}