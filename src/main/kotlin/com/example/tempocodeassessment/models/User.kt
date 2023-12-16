package com.example.tempocodeassessment.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author Run Meng
 * @date 12/15/23  2:28 AM
 */

@Serializable
 data class User (
   @SerialName("id") var id: String,
   @SerialName("firstName") var firstName: String,
   @SerialName("lastName") var lastName: String,
   @SerialName("displayName") var displayName: String,
   @SerialName("avatarUrl") var avatarUrl: String,
   @SerialName("location") var location: String
){
  constructor() : this("","","","","","")
}