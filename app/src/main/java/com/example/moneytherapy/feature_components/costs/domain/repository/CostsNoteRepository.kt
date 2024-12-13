package com.example.moneytherapy.feature_components.costs.domain.repository

import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import kotlinx.coroutines.flow.Flow

interface CostsNoteRepository  {

    suspend fun saveCostsNote(costsNote: CostsNote)

    suspend fun updateCostsNote(costsNote: CostsNote)

    suspend fun getAllMonthCosts() : Flow<List<CostsNote>>

    suspend fun getPersonalCosts() : Flow<List<CostsNote>>

    suspend fun getKnowledgeCosts() : Flow<List<CostsNote>>

    suspend fun getLivingCosts() : Flow<List<CostsNote>>

    suspend fun getUnexpectedCosts() : Flow<List<CostsNote>>

    suspend fun getFunCosts() : Flow<List<CostsNote>>

    suspend fun getOtherCosts() : Flow<List<CostsNote>>

    suspend fun getFixedCosts() : Flow<List<CostsNote>>

}