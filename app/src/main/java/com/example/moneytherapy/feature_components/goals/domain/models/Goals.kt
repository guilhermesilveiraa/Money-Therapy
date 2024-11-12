package com.example.moneytherapy.feature_components.goals.domain.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Goals")
data class Goals(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    var title: String?,
    var value: Int, //Valor conquistado
    var goal: Int, // Valor do objetivo em reais
    var type: Int // 1: Curto Prazo, 2: Médio Prazo, 3: Longo Prazo
) : Parcelable {

    // Sobrescrevendo o metodo que retorna zero por padrão
    override fun describeContents(): Int {
        return 0
    }

    // Implementação do metodo writeToParcel para escrever os dados no Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeInt(value)
        parcel.writeInt(goal)
        parcel.writeInt(type)
    }

    // Objeto CREATOR que serve para recriar o objeto a partir de um Parcel
    companion object CREATOR : Parcelable.Creator<Goals> {
        override fun createFromParcel(parcel: Parcel): Goals {
            // Criando o objeto Goals a partir dos dados presentes no Parcel
            return Goals(
                parcel.readLong(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt()
            )
        }

        override fun newArray(size: Int): Array<Goals?> {
            return arrayOfNulls(size)
        }
    }
}
