package com.sesac.agentmanage

import com.sesac.agentmanage.utils.IOManage

fun main(args: Array<String>) {
    var line: String?
    line = IOManage.consoleScanner()


    while (!line.isNullOrEmpty() && !line.equals("exit", true)) {
        val list = line.split(",")

        line = IOManage.consoleScanner()
    }

//    val temp = mutableListOf<Idolgroup>()
//    temp.add(
//        Idolgroup(
//            "신진혁",
//            2,
//            mutableListOf<Idol>(Idol("신진혁", 2), Idol("신진혁", 2), Idol("신진혁", 2)),
//            Company("sm", "서울", "r김덕배", "01021")
//        )
//    )
//    temp.add(
//        Idolgroup(
//            "신진혁",
//            2,
//            mutableListOf<Idol>(Idol("신진혁", 2), Idol("신진혁", 2), Idol("신진혁", 2)),
//            Company("sm", "서울", "r김덕배", "01021")
//        )
//    )
//    temp.add(
//        Idolgroup(
//            "신진혁",
//            2,
//            mutableListOf<Idol>(Idol("신진혁", 2), Idol("신진혁", 2), Idol("신진혁", 2)),
//            Company("sm", "서울", "r김덕배", "01021")
//        )
//    )
//    temp.add(
//        Idolgroup(
//            "신진혁",
//            2,
//            mutableListOf<Idol>(Idol("신진혁", 2), Idol("신진혁", 2), Idol("신진혁", 2)),
//            Company("sm", "서울", "r김덕배", "01021")
//        )
//    )
//    temp.add(
//        Idolgroup(
//            "신진혁",
//            2,
//            mutableListOf<Idol>(Idol("신진혁", 2), Idol("신진혁", 2), Idol("신진혁", 2)),
//            Company("sm", "서울", "r김덕배", "01021")
//        )
//    )

//    ioManage.multiSerializeObject(temp)
//    ioManage.multiDeserializeObject()
}