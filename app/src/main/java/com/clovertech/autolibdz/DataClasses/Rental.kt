package com.clovertech.autolibdz.DataClasses

import java.sql.Time
import java.util.*

data class Rental (
    val idRental:Int,
    val idVehicle:Int,
    val rentaldate:Date,
    val rentaltime:Time,
    val plannedrestitutiondate:Date,

)