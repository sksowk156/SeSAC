package com.sesac.agentmanage.manage.idolgroup

import com.sesac.agentmanage.data.model.Idol
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.serialize.IdolgroupSerialize
import com.sesac.agentmanage.utils.SerializeInterface

class IdolgroupManage (private val serializedData: SerializeInterface<Idolgroup>) {
    private lateinit var idolgroupList: MutableList<Idolgroup>
    private val idolgroupSerialize = serializedData

    // 전체 아이돌 그룹 조회
    suspend fun getIdolgroupList(): MutableList<Idolgroup> {
        idolgroupList = idolgroupSerialize.DeserializeDataList()
        return idolgroupList
    }

    suspend fun setIdolgroupList(idolgroup: Idolgroup) { // 등록
        if (idolgroupList.add(idolgroup)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        idolgroupSerialize.serializeDataList(idolgroupList)
    }

    suspend fun deleteIdolgroup(idolgroup: Idolgroup) { // 삭제
        if (idolgroupList.remove(idolgroup)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        idolgroupSerialize.serializeDataList(idolgroupList)
    }

    suspend fun updateIdolgroupName(idolgroup: Idolgroup, name: String) { // 그룹명 수정
        val idolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        idolgroup?.name = name
        idolgroupList[idolgroupList.indexOf(idolgroup)] = idolgroup!!

        idolgroupSerialize.serializeDataList(idolgroupList)
    }

    suspend fun updateIdolgroupAddMember(idolgroup: Idolgroup, idol: Idol) { // 멤버 수정 (추가)
        val newIdolgroup: Idolgroup? = idolgroupList.find { it == idolgroup }
        newIdolgroup?.member?.add(idol)
        idolgroupList[idolgroupList.indexOf(idolgroup)] = newIdolgroup!!

        idolgroupSerialize.serializeDataList(idolgroupList)
    }

    suspend fun updateIdolgroupRemoveMember(idolgroup: Idolgroup, idol: Idol) { // 멤버 수정 (삭제)
        val newIdolgroup: Idolgroup = idolgroupList.find { it == idolgroup }!!
        if (newIdolgroup.member.contains(idol)) {
            newIdolgroup?.member?.remove(idol)
            idolgroupList[idolgroupList.indexOf(idolgroup)] = newIdolgroup!!

            idolgroupSerialize.serializeDataList(idolgroupList)
        } else {
            println("해당 아이돌 정보는 없습니다.")
        }
    }
}