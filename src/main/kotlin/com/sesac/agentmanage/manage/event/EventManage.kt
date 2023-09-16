package com.sesac.agentmanage.manage.event

import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.SerializeData
import com.sesac.agentmanage.data.model.Idolgroup

class EventManage private constructor() {
    companion object {
        private var instance: EventManage? = null
        fun getEventManagement(): EventManage =
            instance ?: EventManage().also { instance = it }
    }

    private val serializeData = SerializeData.getSerializeData()
    private val eventList = serializeData.DeserializeEventList()
    fun getEventList() = eventList   // 조회

    fun getEventListByGroupName(name: String) { // 아이돌 그룹명으로 참가 행사 조회
        val eventList = eventList.filter { it.idolgroup.any { it.name == name } }
        if (eventList.isNotEmpty()) {
            println(eventList)
        } else {
            println("해당 아이돌 그룹이 참여하는 행사는 없습니다.")
        }
    }

    fun deleteEvent(event: Event) { // 삭제
        if (eventList.remove(event)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        serializeData.serializeEventList(eventList)
    }

    fun setEventList(Event: Event) { // 등록
        if (eventList.add(Event)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        serializeData.serializeEventList(eventList)
    }

    fun updateEventAddIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.add(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        serializeData.serializeEventList(eventList)
    }

    fun updateEventRemoveIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.remove(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        serializeData.serializeEventList(eventList)
    }
}