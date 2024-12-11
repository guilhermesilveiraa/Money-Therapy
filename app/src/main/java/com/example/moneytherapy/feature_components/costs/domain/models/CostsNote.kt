package com.example.moneytherapy.feature_components.costs.domain.models

import android.os.Parcel
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
    var costType: String,
    var isFixed: Boolean
) : Parcelable {


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(paymentWay)
        installments?.let { parcel.writeInt(it) }
        parcel.writeDouble(value)
        parcel.writeString(costType)
        parcel.writeBoolean(isFixed)
    }

    companion object CREATOR : Parcelable.Creator<CostsNote> {
        override fun createFromParcel(parcel: Parcel): CostsNote {
            return CostsNote(
                id = parcel.readLong(),
                title = parcel.readString(),
                paymentWay = parcel.readString()?:"",
                installments = parcel.readInt(),
                value = parcel.readDouble(),
                costType = parcel.readString()?:"",
                isFixed = parcel.readBoolean()
            )
        }

        override fun newArray(size: Int): Array<CostsNote?> {
            return arrayOfNulls(size)
        }

    }

}
