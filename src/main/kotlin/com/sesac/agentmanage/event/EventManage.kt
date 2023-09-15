package com.sesac.agentmanage.event

import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.SerializeData

class EventManage {
    companion object {
        private var instance: EventManage? = null
        fun getEventManagement(): EventManage =
            instance ?: EventManage().also { instance = it }
    }

    private val eventList = SerializeData.getSerializeData().DeserializeEventList()

    fun getEventList() { // 조회
        println(eventList)
    }

    fun getEventListByName(name: String) { // 조회
        val Event: Event? = eventList.find { it.name == name }
        if (Event != null) {
            println(Event)
        } else {
            println("해당 아이돌 그룹의 정보가 없습니다.")
        }
    }

//    fun getEventListByCompany(name: String) { // 조회
//        val Event: Event? = eventList.find { it.company.name == name }
//        if (Event != null) {
//            println(Event)
//        } else {
//            println("해당 아이돌 그룹의 정보가 없습니다.")
//        }
//    }

    fun setEventList(Event: Event) { // 등록
        eventList.add(Event)
    }

    fun updateEvent(Event: Event, index: Int, name: String) { // 수정
        val Event: Event? = eventList.find { it == Event }
        when (index) {
            1 -> {}
            2 -> {}
            3 -> {}
            else -> {}
        }
    }

    fun deleteEvent() { // 삭제

    }

}