package com.sesac.agentmanage.company

import com.sesac.agentmanage.data.SerializeData
import com.sesac.agentmanage.data.model.Company

import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idolgroup
import java.util.HashMap

class CompanyManage private constructor() {
    companion object {
        private var instance: CompanyManage? = null
        fun getCompanyManagement(): CompanyManage =
            instance ?: CompanyManage().also { instance = it }
    }

    private val serializeData = SerializeData.getSerializeData()
    private val companyList = serializeData.DeserializeCompanyList()

    fun getCompanyList() { //회사 전체 조회
        println(companyList)
    }

    fun getCompanyListByGroupName(name: String) { // 아이돌 그룹명으로 참가 행사 조회
        val companyList = companyList.filter { it.idolgroup.any { it.name == name } }
        if (companyList.isNotEmpty()) {
            println(companyList)
        } else {
            println("해당 아이돌 그룹이 참여하는 행사는 없습니다.")
        }
    }

    fun deleteCompany(company: Company) { // 삭제
        if (companyList.remove(company)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        serializeData.serializeCompanyList(companyList)
    }

    fun setCompanyList(company: Company) { // 등록
        companyList.add(company)

        serializeData.serializeCompanyList(companyList)
    }

    fun updateCompanyAddIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.add(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializeData.serializeCompanyList(companyList)
    }

    fun updateCompanyRemoveIdolgroup(company: Company, idolgroup: Idolgroup) { // 수정
        val newCompany: Company? = companyList.find { it == company }
        newCompany?.idolgroup?.remove(idolgroup)
        companyList[companyList.indexOf(company)] = newCompany!!

        serializeData.serializeCompanyList(companyList)
    }


}