package com.sesac.agentmanage.utils

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idol
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.manage.company.CompanyManage
import com.sesac.agentmanage.manage.event.EventManage
import com.sesac.agentmanage.manage.idolgroup.IdolgroupManage
import java.util.Scanner

class IOManage private constructor() {
    companion object {
        private var instance: IOManage? = null
        fun getIOManage(): IOManage =
            instance ?: IOManage().also { instance = it }

        private lateinit var scanner: Scanner
        fun consoleScanner(): String {
            if (!this::scanner.isInitialized) {
                scanner = Scanner(System.`in`)
            }
            return scanner.nextLine()
        }
    }

    private val companyManage = CompanyManage.getCompanyManagement()
    private val eventManage = EventManage.getEventManagement()
    private val idolgroupManage = IdolgroupManage.getIdolgroupManagement()
    fun askFirstQuestion(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            when (nextLine.toInt()) {
                1 -> {
                    println(eventManage.getEventList())
                    println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트, \"exit\"를 쓰면 종료됩니다.")
                    nextLine = consoleScanner().trim()
                    askEventList(nextLine)
                }

                2 -> {
                    println(companyManage.getCompanyList())
                    println("1. 아이돌 그룹명으로 회사 검색, 2. 회사 삭제, 3. 회사 등록, 4. 회사 정보 업데이트 \"exit\"를 쓰면 종료됩니다.")
                    nextLine = consoleScanner().trim()
                    askCompanyList(nextLine)
                }

                3 -> {
                    println(idolgroupManage.getIdolgroupList())
                    println("1. 아이돌 그룹 추가, 2. 아이돌 그룹 삭제, 3.아이돌 그룹 수정 \"exit\"를 쓰면 종료됩니다.")
                    nextLine = consoleScanner().trim()
                    askIdolgroupList(nextLine)
                }

                else -> {
                    println("잘못치셨어요~")
                }
            }
            println("1. 행사 리스트, 2. 회사 리스트, 3. 아이돌 리스트, \"exit\"를 쓰면 종료됩니다.")
            nextLine = consoleScanner().trim()
        }
        println("종료")
    }

    fun askEventList(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            when (nextLine.toInt()) {
                1 -> {
                    println("아이돌 그룹 이름을 써주세요")
                    nextLine = consoleScanner().trim()
                    eventManage.getEventListByGroupName(nextLine) // 리스트 정보
                }

                2 -> {
                    println("삭제할 행사를 써주세요")
                    nextLine = consoleScanner().trim()
                    val event = eventManage.getEventList().find { it.name == nextLine }
                    if (event != null) {
                        eventManage.deleteEvent(event)
                    } else {
                        println("일치하는 행사가 없습니다.")
                    }
                }

                3 -> {
                    val event = getEvent()
                    eventManage.setEventList(event)
                }

                4 -> {
                    println("편집할 행사의 이름을 쓰세요")
                    nextLine = consoleScanner().trim()
                    askEventListUpdate(nextLine)
                }

                else -> {
                    println("잘못치셨어요~")
                }
            }
            println(eventManage.getEventList())
            println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트, \"exit\"를 쓰면 종료됩니다.")
            nextLine = consoleScanner().trim()
        }
    }

    fun getEvent(): Event {
        println("행상 이름, 행사 날짜, 행사 장소에 대한 정보를 ','로 구분해서 작성해주세요")
        val nextline = consoleScanner().trim()
        val list = nextline.split(",")
        list.forEach { it.trim() }
        return Event(list[0].trim(), list[1].trim(), list[2].trim(), mutableListOf<Idolgroup>())
    }

    fun askEventListUpdate(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            val event = eventManage.getEventList().find { it.name == nextLine }
            if (event != null) {
                println("1. 행사에 참가하는 아이돌 그룹 추가, 2. 삭제")
                var nextLine2 = consoleScanner().trim()
                when (nextLine2.toInt()) {
                    1 -> {
                        println("행사에 추가할 아이돌 그룹명을 쓰세요")
                        var nextLine3 = consoleScanner().trim()
                        val idolgroup =
                            idolgroupManage.getIdolgroupList().find { it.name == nextLine3 }
                        if (idolgroup != null) {
                            eventManage.updateEventAddIdolgroup(event, idolgroup)
                        }
                    }

                    2 -> {
                        println("삭제할 아이돌 그룹명을 쓰세요")
                        var nextLine3 = consoleScanner().trim()
                        val idolgroup =
                            idolgroupManage.getIdolgroupList().find { it.name == nextLine3 }
                        if (idolgroup != null) {
                            eventManage.updateEventRemoveIdolgroup(event, idolgroup)
                        }
                    }

                    else -> {
                        println("번호가 없습니다.")
                    }
                }
            } else {
                println("입력하신 행사 정보는 없습니다, \"exit\"를 쓰면 종료됩니다.")
            }
            println("편집할 행사의 이름을 쓰세요")
            nextLine = consoleScanner().trim()
        }
    }

    fun askCompanyList(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            when (nextLine.toInt()) {
                1 -> {
                    println("아이돌 그룹명을 써주세요")
                    nextLine = consoleScanner().trim()
                    println(companyManage.getCompanyListByGroupName(nextLine))
                }

                2 -> {
                    println("삭제할 회사명을 써주세요")
                    nextLine = consoleScanner().trim()
                    val company = companyManage.getCompanyList().find { it.name == nextLine }
                    if (company != null) {
                        companyManage.deleteCompany(company)
                    } else {
                        println("회사명를 다시 입력하세요")
                    }
                }

                3 -> {
                    val company = getCompany()
                    companyManage.setCompanyList(company)
                }

                4 -> {
                    println("편집할 회사의 이름을 쓰세요")
                    nextLine = consoleScanner().trim()
                    askCompanyListUpdate(nextLine)
                }

                else -> {
                    println("번호가 없습니다")
                }
            }
            println(companyManage.getCompanyList())
            println("1. 아이돌 그룹명으로 회사 검색, 2. 회사 삭제, 3. 회사 등록, 4. 회사 정보 업데이트 \"exit\"를 쓰면 종료됩니다.")
            nextLine = consoleScanner().trim()
        }
    }

    fun getCompany(): Company {
        println("회사명, 회사 주소, CEO 이름, 전화 번호에 대한 정보를 ','로 구분해서 작성해주세요")
        val nextline = consoleScanner().trim()
        val list = nextline.split(",")
        return Company(list[0].trim(), list[1].trim(), list[2].trim(), list[3].trim(), mutableListOf<Idolgroup>())
    }

    fun askCompanyListUpdate(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            val company = companyManage.getCompanyList().find { it.name == nextLine }
            if (company != null) {
                println("1.회사에 소속할 아이돌 추가, 2.회사에 소속한 아이돌 삭제")
                var nextline = consoleScanner().trim()
                when (nextline.toInt()) {
                    1 -> { // 아이돌 추가
                        println("추가할 아이돌 그룹명을 입력하세요 ")
                        val nextline = consoleScanner().trim()
                        val idolgroup =
                            idolgroupManage.getIdolgroupList().find { it.name == nextline }
                        if (idolgroup != null) {
                            companyManage.updateCompanyAddIdolgroup(company, idolgroup)
                        } else {
                            println("해당 그룹명의 아이돌 그룹은 없습니다.")
                        }
                    }

                    2 -> { // 아이돌 삭제
                        println("삭제할 아이돌 그룹명을 입력하세요 ")
                        val nextline = consoleScanner().trim()
                        val idolgroup =
                            idolgroupManage.getIdolgroupList().find { it.name == nextline }
                        if (idolgroup != null) {
                            companyManage.updateCompanyRemoveIdolgroup(company, idolgroup)
                        } else {
                            println("해당 그룹명의 아이돌 그룹은 없습니다.")
                        }
                    }

                    else -> {
                        println("잘못 입력하셨습니다.")
                    }
                }

            } else {
                println("회사명을 다시 입력하세요")
            }
            println("편집할 회사의 이름을 쓰세요 , \"exit\"를 쓰면 종료됩니다.")
            nextLine = consoleScanner().trim()
        }

    }

    fun askIdolgroupList(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            when (nextLine.toInt()) {
                1 -> {
                    val idolgroup = getIdolgroup()
                    idolgroupManage.setIdolgroupList(idolgroup)
                }

                2 -> {
                    println("삭제할 아이돌을 입력하세요")
                    nextLine = consoleScanner().trim()
                    val idol = idolgroupManage.getIdolgroupList().find { it.name == nextLine }
                    if (idol != null) {
                        idolgroupManage.deleteIdolgroup(idol)
                    } else {
                        println("다시 입력하세요")
                    }
                }

                3 -> {
                    println("편집할 아이돌 그룹명을 쓰세요")
                    nextLine = consoleScanner().trim()
                    askIdolListUpdate(line)
                }

                else -> {
                    println("번호가 없습니다.")
                }
            }
            println(idolgroupManage.getIdolgroupList())
            println("1. 아이돌 그룹 추가, 2. 아이돌 그룹 삭제, 3.아이돌 그룹 수정 \"exit\"를 쓰면 종료됩니다.")
            nextLine = consoleScanner().trim()
        }
    }

    fun getIdolgroup(): Idolgroup {
        println("그룹명을 입력해주세요")
        val groupName = consoleScanner().trim()
        val idolList = getIdolList()
        return Idolgroup(groupName, idolList.size, idolList)
    }

    fun getIdolList(): MutableList<Idol> {
        val idolList = mutableListOf<Idol>()
        println("그룹 멤버")
        println("아이돌 이름, 아이돌 나이에 대한 정보를 ','로 구분해서 입력해주세요, \"exit\"를 쓰면 종료됩니다.")
        var nextline = consoleScanner().trim()
        while (!nextline.isNullOrEmpty() && !nextline.equals("exit", true)) {
            var list = nextline.split(",")
            idolList.add(Idol(list[0].trim(), list[1].trim().toInt()))
            nextline = consoleScanner().trim()
        }
        return idolList
    }

    fun askIdolListUpdate(line: String?) {
        var nextLine = line
        while (!nextLine.isNullOrEmpty() && !nextLine.equals("exit", true)) {
            val idolgroup = idolgroupManage.getIdolgroupList().find { it.name == nextLine }
            if (idolgroup != null) {
                println("1. 그룹 명 수정, 2. 걸그룹 멤버 추가, 3.걸그룹 멈버 삭제")
                var nextline = consoleScanner().trim()
                when (nextline.toInt()) {
                    1 -> {
                        println("수정 할 이름을 말하세요")
                        val nextline = consoleScanner().trim()
                        idolgroupManage.updateIdolgroupName(idolgroup, nextline)
                    }

                    2 -> {
                        val idol = getIdol()
                        idolgroupManage.updateIdolgroupAddMember(idolgroup, idol)
                    }

                    3 -> {
                        val idol = getIdol()
                        idolgroupManage.updateIdolgroupRemoveMember(idolgroup, idol)
                    }

                    else -> {
                        println("번호가 없습니다.")
                    }
                }
            }
            println("편집할 아이돌 그룹명을 쓰세요")
            nextLine = consoleScanner().trim()
        }
    }

    fun getIdol(): Idol {
        println("아이돌의 이름, 나이를 ','로 구분해서 작성해주세요")
        val nextline = consoleScanner().trim()
        val list = nextline.split(",")
        list.forEach { it.trim() }
        return Idol(list[0].trim(), list[1].trim().toInt())
    }
}