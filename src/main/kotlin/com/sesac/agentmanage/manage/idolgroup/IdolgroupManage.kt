package com.sesac.agentmanage.manage.idolgroup

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

    // 전체 아이돌 그룹 조회
    fun getIdolgroupList() = idolgroupList

    fun setIdolgroupList(idolgroup: Idolgroup) { // 등록
        if (idolgroupList.add(idolgroup)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        serializeData.serializeIdolgroupList(idolgroupList)
    }

    fun deleteIdolgroup(idolgroup: Idolgroup) { // 삭제
        if (idolgroupList.remove(idolgroup)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        serializeData.serializeIdolgroupList(idolgroupList)
    }

    fun updateIdolgroupName(idolgroup: Idolgroup, name: String) { // 그룹명 수정
        val idolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        idolgroup?.name = name
        idolgroupList[idolgroupList.indexOf(idolgroup)] = idolgroup!!

        serializeData.serializeIdolgroupList(idolgroupList)
    }

    fun updateIdolgroupAddMember(idolgroup: Idolgroup, idol: Idol) { // 멤버 수정 (추가)
        val newIdolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        newIdolgroup?.member?.add(idol)
        idolgroupList[idolgroupList.indexOf(idolgroup)] = newIdolgroup!!

        serializeData.serializeIdolgroupList(idolgroupList)
    }

    fun updateIdolgroupRemoveMember(idolgroup: Idolgroup, idol: Idol) { // 멤버 수정 (삭제)
        val newIdolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        newIdolgroup?.member?.remove(idol)
        idolgroupList[idolgroupList.indexOf(idolgroup)] = newIdolgroup!!

        serializeData.serializeIdolgroupList(idolgroupList)
    }
}