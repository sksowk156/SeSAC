package com.sesac.agentmanage

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idol
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.manage.company.CompanyManage
import com.sesac.agentmanage.manage.event.EventManage
import com.sesac.agentmanage.manage.idolgroup.IdolgroupManage
import com.sesac.agentmanage.utils.IOManage

fun main(args: Array<String>) {
    var line: String?
    println("1. 행사 리스트, 2. 회사 리스트, 3. 아이돌 리스트, \"exit\"를 쓰면 종료됩니다.")
    line = IOManage.consoleScanner().trim()
    IOManage.getIOManage().askFirstQuestion(line)
}
