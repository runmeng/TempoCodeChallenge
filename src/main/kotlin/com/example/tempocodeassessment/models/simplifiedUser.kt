package com.example.tempocodeassessment.models

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Run Meng
 * @date 12/15/23  2:50 AM
 */
@Serializable
data class simplifiedUser (
    @SerialName("id") var id: String,
    @SerialName("displayName") var displayName: String
){
    constructor() : this("","")
}