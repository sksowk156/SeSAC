package com.sesac.agentmanage.idolgroup

import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.data.SerializeData
import com.sesac.agentmanage.data.model.Idol

class IdolgroupManage private constructor() {
    companion object {
        private var instance: IdolgroupManage? = null
        fun getIdolgroupManagement(): IdolgroupManage =
            instance ?: IdolgroupManage().also { instance = it }
    }
    private val serializeData = SerializeData.getSerializeData()
    private val idolgroupList = serializeData.DeserializeIdolgroupList()

    fun getIdolgroupList() { // 조회
        println(idolgroupList)
    }

    fun getIdolgroupByName(name: String) { // 조회
        val idolgroup: Idolgroup? = idolgroupList.find { it.name == name }
        if (idolgroup != null) {
            println(idolgroup)
        } else {
            println("해당 아이돌 그룹의 정보가 없습니다.")
        }
    }

    fun getIdolgroupByCompany(name: String) { // 조회
        val idolgroup: Idolgroup? = idolgroupList.find { it.company.name == name }
        if (idolgroup != null) {
            println(idolgroup)
        } else {
            println("해당 아이돌 그룹의 정보가 없습니다.")
        }
    }

    fun setIdolgroupList(idolgroup: Idolgroup) { // 등록
        idolgroupList.add(idolgroup)
    }

    fun updateIdolgroup(idolgroup: Idolgroup, index: Int, name: String) { // 수정
        val idolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        when (index) {
            1 -> {}
            2 -> {}
            3 -> {}
            else -> {}
        }
    }

    fun addIdol(idol: Idol){
        
    }

    fun deleteIdolgroup(idolgroup: Idolgroup) { // 삭제
        if(idolgroupList.remove(idolgroup)){
            println("삭제 성공")
        }else{
            println("삭제 실패")
        }
        serializeData.serializeIdolgroupList(idolgroupList)
    }

}