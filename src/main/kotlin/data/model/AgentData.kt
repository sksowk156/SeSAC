package data.model

import java.io.Serializable

data class Event(
    val name: String, // 행사 이름
    val date: String, // 행사 날짜
    val place: Array<String>, // 행사 장소
    val idolgroup: MutableList<Idolgroup> // 행사 참가 아이돌 그룹
) : Serializable
data class Idolgroup(
    val name: String, // 그룹 이름
    val count: Int, // 인원수
    val member: MutableList<Idol>, // 아이돌
    val company: Company, // 소속 회사명
//    val event: MutableList<Event> // 참가 행사 목록
) : Serializable
data class Idol(
    val name: String,
    val age: Int
) : Serializable

data class Company(
    val name: String, // 회사명
    val address: String, // 주소
    val ceo: String, // 사장 이름
    val phoneNumber: String, // 전화 번호
//    val idolgroup: MutableList<Idolgroup> // 소속 아이돌 그룹
) : Serializable