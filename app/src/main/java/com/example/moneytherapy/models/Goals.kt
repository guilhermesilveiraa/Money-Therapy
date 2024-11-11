package com.example.moneytherapy.models

import android.os.Parcel
import android.os.Parcelable

data class Goals(
    var title: String?,
    var goal: Int, // Valor do objetivo em reais
    var type: Int // 1: Curto Prazo, 2: Médio Prazo, 3: Longo Prazo
) : Parcelable {

    // Sobrescrevendo o método que retorna zero por padrão
    override fun describeContents(): Int {
        return 0
    }

    // Implementação do método writeToParcel para escrever os dados no Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeInt(goal)
        parcel.writeInt(type)
    }

    // Objeto CREATOR que serve para recriar o objeto a partir de um Parcel
    companion object CREATOR : Parcelable.Creator<Goals> {
        override fun createFromParcel(parcel: Parcel): Goals {
            // Criando o objeto Goals a partir dos dados presentes no Parcel
            return Goals(
                parcel.readString(),
                parcel.readInt(),
                parcel.readInt()
            )
        }

        override fun newArray(size: Int): Array<Goals?> {
            return arrayOfNulls(size)
        }
    }
}
