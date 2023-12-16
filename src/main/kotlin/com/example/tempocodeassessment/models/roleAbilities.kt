package com.example.tempocodeassessment.models

/**
 * @Author Run Meng
 * @date 12/15/23  4:33 AM
 */
enum class roleAbilities(val role: String, val abilities: Array<String>) {
    DEVELOPER("Developer", arrayOf("Develop","Release","Update")),
    PRODUCT_OWENER("Product_Owner", arrayOf("Guide","Arrange","Communication")),
    TESTER("Tester", arrayOf("Debug","Improve","Document"));

    companion object {

        fun fromRoleName(role: String): Array<String> {
            return values().find { it.role == role }!!.abilities
        }
    }
}