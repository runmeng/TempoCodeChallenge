package com.example.tempocodeassessment.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Run Meng
 * @date 12/15/23  3:07 AM
 */
@Serializable
data class Team(
    @SerialName("id") var id: String,
    @SerialName("name") var name: String,
    @SerialName("teamLeadId") var teamLeadId: String,
    @SerialName("teamMemberIds") var teamMemberIds: Array<String>,
){
    constructor() : this("","","", emptyArray())
}