package com.example.moneytherapy.feature_components.core.data.local.repository

import com.example.moneytherapy.feature_components.core.data.local.dao.CostsNoteDao
import com.example.moneytherapy.feature_components.costs.domain.models.CostsNote
import com.example.moneytherapy.feature_components.costs.domain.repository.CostsNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CostsNoteRepositoryImpl @Inject constructor(
    private val costsNoteDao: CostsNoteDao
): CostsNoteRepository{

    override suspend fun saveCostsNote(costsNote: CostsNote) {
        costsNoteDao.save(costsNote)
    }

    override suspend fun updateCostsNote(costsNote: CostsNote) {
        costsNoteDao.update(costsNote)
    }

    override suspend fun getAllMonthCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllCostsByMonth(
            startOfMonth = TODO(),
            endOfMonth = TODO()
        )
    }

    override suspend fun getPersonalCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllPersonalCosts()
    }

    override suspend fun getKnowledgeCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllKnowledgeCosts()
    }

    override suspend fun getLivingCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllLivingCosts()
    }

    override suspend fun getUnexpectedCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllUnexpectedCosts()
    }

    override suspend fun getFunCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllFunCosts()
    }

    override suspend fun getOtherCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllOtherCosts()
    }

    override suspend fun getFixedCosts(): Flow<List<CostsNote>> {
        return costsNoteDao.getAllFixedCosts()
    }


}