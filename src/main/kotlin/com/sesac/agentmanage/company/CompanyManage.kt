package com.sesac.agentmanage.company

import com.sesac.agentmanage.data.model.Company
import java.util.HashMap

class CompanyManage private constructor() {

    companion object {
        private var instance: CompanyManage? = null
        val companyMap = HashMap<String, Company>()
        fun getCompanyManagement(): CompanyManage =
            instance ?: CompanyManage().also {
                instance = it
            }
    }

    fun getAllCompany() {                   //회사 전체 조회
        println(companyMap)
    }

    fun getCompany(name: String) {          // 조회
        println(companyMap.filterValues {
            it.equals(name)
        })
    }

    fun setCompany(company: Company, newCompany: Company) {                     // 회사 정보 수정
        //        companyMap[company.name] = newCompany
        if (companyMap.containsValue(company)) {

        } else {

        }
    }


    fun updateCompany(name: String) {                         // 회사 등록
//        val company: Company?
        if (companyMap.contains(name)) {
            println("이미 있는 회사 입니다.")
        } else {
            println("콤마를 기준으로 회사이름,주소,대표이름,전화번호를 입력해주세요. ex)SM,서울성동구,02-000-000")
          //  companyMap.put()
        }
    }


    fun deleteCompany(name: String) {               //삭제
        if (companyMap.contains(name)) {
            companyMap.remove(name)
        }
    }
}

