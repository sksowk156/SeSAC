package com.sesac.agentmanage.manage.event

import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.serialize.EventSerialize

class EventManage private constructor() {
    companion object {
        private var instance: EventManage? = null
        fun getEventManagement(): EventManage = instance ?: EventManage().also { instance = it }
    }

    private lateinit var eventList: MutableList<Event>
    private val eventSerialize = EventSerialize()

    suspend fun getEventList(): MutableList<Event> { // 조회
        eventList = eventSerialize.DeserializeDataList()
        return eventList
    }

    fun getEventListByGroupName(name: String) { // 아이돌 그룹명으로 참가 행사 조회
        val eventList = eventList.filter { it.idolgroup.any { it.name == name } }
        if (eventList.isNotEmpty()) {
            println(eventList)
        } else {
            println("해당 아이돌 그룹이 참여하는 행사는 없습니다.")
        }
    }

    suspend fun deleteEvent(event: Event) { // 삭제
        if (eventList.remove(event)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        eventSerialize.serializeDataList(eventList)
    }

//    fun getEventListByIdolgroup(idolgroup: Idolgroup) = eventList.filter { it.idolgroup.any { it == idolgroup } }
//    fun updateEventListByIdolgroup(eventListIdolgroup: List<Event>, idolgroup: Idolgroup) { // 수정
//        getEventListByIdolgroup(idolgroup).forEach { i ->
//            val idx = eventList.indexOf(i)
//            i.idolgroup.remove(idolgroup)
//            eventList[idx] = i
//        }
//    }

    suspend fun setEventList(Event: Event) { // 등록
        if (eventList.add(Event)) {
            println("등록 성공")
        } else {
            println("등록 실패")
        }

        eventSerialize.serializeDataList(eventList)
    }

    suspend fun updateEventAddIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.add(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        eventSerialize.serializeDataList(eventList)
    }

    suspend fun updateEventRemoveIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.remove(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        eventSerialize.serializeDataList(eventList)
    }
}