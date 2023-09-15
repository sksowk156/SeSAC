package com.sesac.agentmanage.company

import com.sesac.agentmanage.data.SerializeData
import com.sesac.agentmanage.data.model.Company

class CompanyManage private constructor() {
    companion object {
        private var instance: CompanyManage? = null
        fun getCompanyManagement(): CompanyManage =
            instance ?: CompanyManage().also { instance = it }
    }

    private val serializeData = SerializeData.getSerializeData()
    private val companyList = serializeData.DeserializeCompanyList()

    fun getAllCompany() {                   //회사 전체 조회
        println(companyList)
    }

    fun getCompanyByName(name: String) {          // 조회
        if (companyList.equals(name)) {

        } else {
            println("회사 명을 다시 입력해주세요")
        }
    }


    /*
        fun setCompany(company: Company, newCompany: Company) {                     // 회사 정보 수정
            //        companyMap[company.name] = newCompany
            if (companyList.containsValue(company)) {

            } else {

            }
        }
    */


    fun updateCompany(name: String) {                         // 회사 등록
//        val company: Company?
        if (companyList.equals(name)) {
            println("이미 있는 회사 입니다.")
        } else {
            println("콤마를 기준으로 회사이름,주소,대표이름,전화번호를 입력해주세요. ex)SM,서울성동구,02-000-000")
            companyList
        }
    }


    fun deleteCompany(name: String) {               //삭제
        if (companyList.equals(name)) {
            companyList.
        }
    }


}