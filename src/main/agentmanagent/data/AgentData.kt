package data

data class Company(
    val name: String,
    val address: String,
    val ceo: String,
    val phoneNumber: String
)

data class Event(
    val name: String,
    val date: String,
    val place: Array<String>,
    val idolgroup: MutableList<Idolgroup>
)

data class Idol(
    val name: String,
    val age: Int
)

data class Idolgroup(
    val name: String,
    val count: Int,
    val member: MutableList<Idol>,
    val company: Company
)
