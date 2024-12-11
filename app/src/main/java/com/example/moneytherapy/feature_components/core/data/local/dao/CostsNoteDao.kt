package com.example.moneytherapy.feature_components.core.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import kotlinx.coroutines.flow.Flow

interface CostsNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(costsNote:CostsNote)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(costsNote: CostsNote)

    //Queries por tipo de custo
    @Query("SELECT * FROM CostsNote WHERE costType = 'Pessoal' ")
    fun getAllPersonalCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE costType = 'Conhecimento'")
    fun getAllKnowledgeCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE costType = 'Moradia'")
    fun getAllLivingCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE costType = 'Imprevistos'")
    fun getAllUnexpectedCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE costType = 'Lazer'")
    fun getAllFunCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE costType = 'Outros'")
    fun getAllOtherCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE isFixed = false ")
    fun getAllVariableCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE isFixed = true")
    fun getAllFixedCosts() : Flow<List<CostsNote>>

    //Queries por tipo de pagamento
    @Query("SELECT * FROM CostsNote WHERE paymentWay = 'Credito'")
    fun getAllCreditCosts() : Flow<List<CostsNote>>

    @Query("SELECT * FROM CostsNote WHERE paymentWay = 'Dinheiro'")
    fun getAllMoneyCosts() : Flow<List<CostsNote>>






}