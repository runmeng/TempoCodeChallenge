package com.example.tempocodeassessment.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Run Meng
 * @date 12/15/23  3:09 AM
 */

@Serializable
data class simplifiedTeam(
    @SerialName("id") var id: String,
    @SerialName("name") var name: String
){
    constructor() : this("","")
}
