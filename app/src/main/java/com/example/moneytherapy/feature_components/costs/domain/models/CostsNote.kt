package com.example.moneytherapy.feature_components.costs.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CostsNote")
data class CostsNote(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var title: String?,
    var paymentWay: String,
    var installments: Int?, //If it's intallment the next month must contains the parcel from these cost
    var value: Double,
    var costType: String
) : Parcelable {

}
