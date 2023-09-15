package com.sesac.agentmanage

import com.sesac.agentmanage.manage.company.CompanyManage
import com.sesac.agentmanage.manage.event.EventManage
import com.sesac.agentmanage.manage.idolgroup.IdolgroupManage
import com.sesac.agentmanage.utils.IOManage

fun main(args: Array<String>) {
    var line: String?
    val companyManage = CompanyManage.getCompanyManagement()
    val eventManage = EventManage.getEventManagement()
    val idolgroupManage = IdolgroupManage.getIdolgroupManagement()
    println("1 : 행사리스트, 2 : 회사리스트, 3 : 아이돌리스트")
    line = IOManage.consoleScanner()
    askFirstQuestion(line)

    when (line.toInt()) {
        1 -> {
            companyManage.getCompanyList()  // 리스트 정보 출력

            println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트")
            line = IOManage.consoleScanner()
            when (line.toInt()) {
                1 -> {
                    println("아이돌 그룹 이름을 써주세요")
                    line = IOManage.consoleScanner()
                    companyManage.getCompanyListByGroupName(line) // 리스트 정보
                    line = IOManage.consoleScanner()
                    println("엔터치세요")
                    if (line == "") {

                    }
                }

                2 -> {

                }

                3 -> {

                }

                4 -> {

                }
            }
        }

        2 -> {

        }

        3 -> {

        }

        else -> {

        }
    }

    // 1 : 행사리스트, 2 : 회사리스트, 3 : 아이돌리스트
    // 리스트 정보 출력
    // 1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트 -> 1.아이돌 그룹 추가, 2. 아이돌 그룹 삭제
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        val list = line.split(",")

        line = IOManage.consoleScanner()
    }
}

fun askFirstQuestion(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("1 : 행사리스트, 2 : 회사리스트, 3 : 아이돌리스트, \"exit\"를 쓰면 종료됩니다.")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {
                println(EventManage.getEventManagement().getEventList())
                askEventList(nextline)
            }

            2 -> {
                CompanyManage.getCompanyManagement().getCompanyList()
                askCompanyList(nextline)
            }

            3 -> {
                IdolgroupManage.getIdolgroupManagement().getIdolgroupList()
                askIdolgroupList(nextline)
            }

            else -> {
                println("잘못치셨어요~")
            }
        }
    }
}

fun askEventList(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {
                println("아이돌 그룹 이름을 써주세요")
                nextline = IOManage.consoleScanner()
                EventManage.getEventManagement().getEventListByGroupName(nextline) // 리스트 정보
            }

            2 -> {

            }

            3 -> {

            }

            4 -> {
                askEventListUpdate(nextline)
            }

            else -> {
                println("잘못치셨어요~")
            }
        }
    }
}

fun askEventListUpdate(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("편집할 행사의 이름 쓰세요")
        var templine = IOManage.consoleScanner()
        val event = EventManage.getEventManagement().getEventList().find { it.name == templine }
        if (event != null) {
            println("1. 행사에 참가하는 아이돌 그룹 추가, 2. 삭제")
            var nextline = IOManage.consoleScanner()
            when (nextline.toInt()) {
                1 -> {
                    println("아이돌 그룹명을 쓰세요")
                    var nextline2 = IOManage.consoleScanner()
                    val idolgroup =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline2 }
                    if (idolgroup != null) {
                        EventManage.getEventManagement().updateEventAddIdolgroup(event, idolgroup)
                    }
                }

                2 -> {
                    println("아이돌 그룹명을 쓰세요")
                    var nextline2 = IOManage.consoleScanner()
                    val idolgroup =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline2 }
                    if (idolgroup != null) {
                        EventManage.getEventManagement().updateEventRemoveIdolgroup(event, idolgroup)
                    }
                }

                else -> {
                    println("번호가 없습니다.")
                }
            }
        } else {
            println("행사명을 다시 입력하세요")
        }
    }
}

fun askCompanyList(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("1 : 행사리스트, 2 : 회사리스트, 3 : 아이돌리스트")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {

            }

            2 -> {

            }

            3 -> {

            }

            else -> {

            }
        }
    }
}

fun askIdolgroupList(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("1 : 행사리스트, 2 : 회사리스트, 3 : 아이돌리스트")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {

            }

            2 -> {

            }

            3 -> {

            }

            else -> {

            }
        }
    }
}