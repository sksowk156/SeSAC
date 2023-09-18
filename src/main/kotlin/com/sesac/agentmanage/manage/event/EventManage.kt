package com.sesac.agentmanage.manage.event

import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.utils.SerializeInterface

class EventManage (private val serializedData: SerializeInterface<Event>) {
    private lateinit var eventList: MutableList<Event>
    suspend fun getEventList(): MutableList<Event> { // 조회
        eventList = serializedData.DeserializeDataList()
        return eventList
    }

    fun getEventListByGroupName(name: String) = eventList.filter { it.idolgroup.any { it.name == name } }

    suspend fun deleteEvent(event: Event) { // 삭제
        if (eventList.remove(event)) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }
        serializedData.serializeDataList(eventList)
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

        serializedData.serializeDataList(eventList)
    }

    suspend fun updateEventAddIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.add(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        serializedData.serializeDataList(eventList)
    }

    suspend fun updateEventRemoveIdolgroup(event: Event, idolgroup: Idolgroup) { // 수정
        val newEvent: Event? = eventList.find { it == event }
        newEvent?.idolgroup?.remove(idolgroup)
        eventList[eventList.indexOf(event)] = newEvent!!

        serializedData.serializeDataList(eventList)
    }
}