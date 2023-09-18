package com.sesac.agentmanage.utils

interface SerializeInterface<T> {
    suspend fun serializeDataList(list: MutableList<T>): String
    suspend fun DeserializeDataList(): MutableList<T>
}