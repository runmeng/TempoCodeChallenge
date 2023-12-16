package com.example.tempocodeassessment.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Run Meng
 * @date 12/15/23  5:01 AM
 */

@Serializable
data class roleAndTeam(
    @SerialName("name") var name: String,
    @SerialName("role") var role: String,
    @SerialName("ability") var ability: Array<String>,
    @SerialName("team") var team: Array<String>
    ){
    constructor() : this("","", emptyArray(), emptyArray())
}
