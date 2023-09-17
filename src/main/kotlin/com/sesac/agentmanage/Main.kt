package com.sesac.agentmanage

import com.sesac.agentmanage.data.model.Company
import com.sesac.agentmanage.data.model.Event
import com.sesac.agentmanage.data.model.Idol
import com.sesac.agentmanage.data.model.Idolgroup
import com.sesac.agentmanage.manage.company.CompanyManage
import com.sesac.agentmanage.manage.event.EventManage
import com.sesac.agentmanage.manage.idolgroup.IdolgroupManage
import com.sesac.agentmanage.utils.IOManage

suspend fun main(args: Array<String>) {
    IOManage.getIOManage().askFirstQuestion()
}
