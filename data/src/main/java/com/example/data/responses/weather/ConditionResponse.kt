package com.example.data.responses.weather


import com.google.gson.annotations.SerializedName

data class ConditionResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
)