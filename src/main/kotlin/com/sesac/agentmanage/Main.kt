package com.sesac.agentmanage

import com.sesac.agentmanage.data.model.Event
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
            //  companyManage.getCompanyList()  // 리스트 정보 출력
            println(eventManage.getEventList())
            println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트, \"exit\"를 쓰면 종료됩니다.")
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
                    println("삭제할 행사를 입력해주세요")
                    line = IOManage.consoleScanner()
                }

                3 -> {
                    println("추가할 행사를 입력해주세요")
                    line = IOManage.consoleScanner()
                }

                4 -> {

                }

                else -> {
                    println("잘못 입력하셨습니다.")
                }
            }
        }

        2 -> {
            println(companyManage.getCompanyList())
            println("1. 아이돌 그룹으로 회사 조회, 2. 회사 삭제, 3.회사 등록 4. 회사 정보 업데이트 \"exit\"를 쓰면 종료됩니다.")
            line = IOManage.consoleScanner()
            when (line.toInt()) {
                1 -> {

                }

                2 -> {

                }

                3 -> {

                }

                4 -> {

                }

                else -> {

                }

            }
        }

        3 -> {
            println(idolgroupManage.getIdolgroupList())

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
        println("1. 아이돌 그룹이 참가하는 행사 찾기, 2. 행사 삭제, 3. 행사 추가, 4. 행사 정보 업데이트, \"exit\"를 쓰면 종료됩니다.")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {
                println("아이돌 그룹 이름을 써주세요")
                nextline = IOManage.consoleScanner()
                EventManage.getEventManagement().getEventListByGroupName(nextline) // 리스트 정보
            }

            2 -> {
                println("삭제할 행사를 써주세요")
                nextline = IOManage.consoleScanner()
                val event = EventManage.getEventManagement().getEventList().find { it.name == nextline }
                if (event != null) {
                    EventManage.getEventManagement().deleteEvent(event)
                } else {
                    println("행사명을 다시 입력하세요")
                }
            }

            3 -> {
                println("추가할 행사를 써주세요")
                nextline = IOManage.consoleScanner()
                val event = EventManage.getEventManagement().getEventList().find { it.name != nextline }

                // TODO 형 등록할 회사가 CompanyList 안에 없어야 하잖아요 그럼 find 로 이렇게 처리하면 null이 반환이 될텐데 어떤 코드를 써야할지 모르겠어요..

            }

            4 -> {
                askEventListUpdate(nextline)
                // TODO 이게 맞나여..?
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
            println("1. 행사에 참가하는 아이돌 그룹 추가, 2. 삭제, \"exit\"를 쓰면 종료됩니다.")
            var nextline = IOManage.consoleScanner()
            when (nextline.toInt()) {
                1 -> {
                    println("행사에 추가할 아이돌 그룹명을 쓰세요")
                    var nextline2 = IOManage.consoleScanner()
                    val idolgroup =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline2 }
                    if (idolgroup != null) {
                        EventManage.getEventManagement().updateEventAddIdolgroup(event, idolgroup)
                    }
                }

                2 -> {
                    println("삭제할 아이돌 그룹명을 쓰세요")
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
        println("1. 아이돌 그룹명으로 회사 검색, 2. 회사 삭제, 3. 회사 등록, 4. 회사 정보 업데이트 \"exit\"를 쓰면 종료됩니다.")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {
                println("아이돌 그룹 이름을 써주세요")
                nextline = IOManage.consoleScanner()
                CompanyManage.getCompanyManagement().getCompanyListByGroupName(nextline)
            }

            2 -> {
                println("삭제할 회사를 써주세요")
                nextline = IOManage.consoleScanner()
                val company = CompanyManage.getCompanyManagement().getCompanyList().find { it.name == nextline }
                if (company != null) {
                    CompanyManage.getCompanyManagement().deleteCompany(company)
                } else {
                    println("회사를 다시 입력하세요")
                }
            }

            3 -> {
                println("등록할 회사를 써주세요")
                nextline = IOManage.consoleScanner()
                val company = CompanyManage.getCompanyManagement().getCompanyList().find { it.name != nextline }
                // TODO 형 등록할 회사가 CompanyList 안에 없어야 하잖아요 그럼 find 로 이렇게 처리하면 null이 반환이 될텐데 어떤 코드를 써야할지 모르겠어요..
            }

            4 -> {
                askCompanyListUpdate(nextline)
                // TODO 이게 맞나요..?
            }

            else -> {
                println("번호가 없습니다")
            }
        }
    }
}

fun askCompanyListUpdate(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("편집할 회사의 이름 쓰세요")
        var templine = IOManage.consoleScanner()
        val company = CompanyManage.getCompanyManagement().getCompanyList().find { it.name == templine }
        if (company != null) {
            println("1.회사에 소속할 아이돌 추가, 2.회사에 소속한 아이돌 삭제  \"exit\"를 쓰면 종료됩니다.")
            var nextline = IOManage.consoleScanner()
            when (nextline.toInt()) {
                1 -> {
                    println("아이돌 그룹명을 입력하세요 ")
                    val nextline = IOManage.consoleScanner()
                    val idolgroup =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline }
                    if (idolgroup != null) {
                        CompanyManage.getCompanyManagement().updateCompanyAddIdolgroup(company, idolgroup)
                    }
                }

                2 -> {
                    val nextline = IOManage.consoleScanner()
                    val idolgroup =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline }
                    if (idolgroup != null) {
                        CompanyManage.getCompanyManagement().updateCompanyRemoveIdolgroup(company, idolgroup)
                    }

                }

                else -> {
                    println("번호가 없습니다")
                }
            }

        } else {
            println("회사명을 다시 입력하세요")
        }

    }

}

fun askIdolgroupList(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("1. 아이돌 그룹의 인원 확인, 2. 아이돌 삭제, 3 : 아이돌 추가, 4.아이돌 그룹 수정 \"exit\"를 쓰면 종료됩니다.")
        var nextline = IOManage.consoleScanner()
        when (nextline.toInt()) {
            1 -> {
                println("확인할 아이돌 그룹을 입력하세요")
                nextline = IOManage.consoleScanner()
                // TODO
            }

            2 -> {
                println("삭제할 아이돌을 입력하세요")
                nextline = IOManage.consoleScanner()
                val idol = IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline }
                if (idol != null) {
                    IdolgroupManage.getIdolgroupManagement().deleteIdolgroup(idol)
                } else {
                    println("다시 입력하세요")
                }
            }

            3 -> {
                println("추가할 아이돌을 입력하세요")
                nextline = IOManage.consoleScanner()
                // TODO 잘모르겠어영...
            }

            4 -> {
                askIdolListUpdate(line)
                // TODO 이게 맞나요..?
            }

            else -> {
                println("번호가 없습니다.")
            }
        }
    }
}

fun askIdolListUpdate(line: String?) {
    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        println("편집할 아이돌 그룹명을 쓰세요")
        var templine = IOManage.consoleScanner()
        val idol = IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == templine }
        if (idol != null) {
            println("1. 그룹 명 수정, 2. 걸그룹 멤버 추가, 3.걸그룹 멈버 삭제")
            var nextline = IOManage.consoleScanner()
            when (nextline.toInt()) {
                1 -> {
                    println("수정 할 이름을 말하세요")
                    val nextline = IOManage.consoleScanner()
                    val changeName =
                        IdolgroupManage.getIdolgroupManagement().getIdolgroupList().find { it.name == nextline }
                    if (changeName != null) {
                        IdolgroupManage.getIdolgroupManagement().updateIdolgroupName(changeName, nextline)
                    }
                }

                2 -> {
                    // TODO updateIdolgroupAddMember 수정 추가

                }

                3 -> {
                    // TODO updateIdolgroupRemoveMember 수정 삭제
                }

                else -> {
                    println("번호가 없습니다.")
                }
            }
        }
    }
}
