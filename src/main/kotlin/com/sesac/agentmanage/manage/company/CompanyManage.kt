package com.sesac.agentmanage.manage.company

import com.sesac.agentmanage.data.SerializeData
import com.sesac.agentmanage.data.model.Company

import com.sesac.agentmanage.data.model.Idolgroup

class CompanyManage private constructor() {
    companion object {
        private var instance: CompanyManage? = null
        fun getCompanyManagement(): CompanyManage =
            instance ?: CompanyManage().also { instance = it }
    }

    private val serializeData = SerializeData.getSerializeData()
    private lateinit var companyList : MutableList<Company>
    //회사 전체 조회
    suspend fun getCompanyList() : MutableList<Company>{
        companyList = serializeData.DeserializeCompanyList()
        return companyList
    }

    suspend fun getCompanyListByGroupName(name: String) { // 아이돌 그룹명으로 소속된 회사 조회
        val company = companyList.find { it.idolgroup.any { it.name == name } }
        if (company != null) {
            println(company)
        } else {
            println("해당 아이돌 그룹이 소속된 회사는 없습니다.")
        }
    }

    suspend fun deleteCompany(company: Company) { // 삭제
        if (companyList.remove(company)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }

        serializeData.serializeCompanyList(companyList)
    }

    suspend fun setCompanyList(company: Company) { // 등록
        if(companyList.add(company)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        serializeData.serializeCompanyList(companyList)
    }


    suspend fun updateCompanyAddIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정, 회사에 아이돌 그룹 추가
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.add(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializeData.serializeCompanyList(companyList)
    }

    suspend fun updateCompanyRemoveIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.remove(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializeData.serializeCompanyList(companyList)
    }


}