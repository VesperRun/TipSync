package com.vesperrun.tipsync.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GigRepository(
    private val dao: GigDao,
    private val io: CoroutineDispatcher = Dispatchers.IO,
) {
    fun observeGigs(): Flow<List<GigEntity>> = dao.observeGigs()

    fun observeInbox(): Flow<List<GigEntity>> = dao.observeInbox()

    fun observeCastOwedTotal(): Flow<Int> = dao.observeCastOwedTotal()

    fun observeCast(gigId: String): Flow<List<CastLineEntity>> = dao.observeCastForGig(gigId)

    suspend fun gigById(id: String): GigEntity? = withContext(io) { dao.gigById(id) }

    suspend fun ensureSeeded() =
        withContext(io) {
            if (dao.gigCount() > 0) return@withContext
            dao.insertGigs(SeedGigs.gigs())
            dao.insertCast(SeedGigs.cast())
        }

    suspend fun toggleCastPaid(line: CastLineEntity) =
        withContext(io) {
            dao.updateCastLine(line.copy(paid = !line.paid))
        }

    suspend fun toggleGuaranteeReceived(gig: GigEntity) =
        withContext(io) {
            dao.updateGig(gig.copy(guaranteeReceived = !gig.guaranteeReceived))
        }

    fun castOwedTotal(gigId: String, cast: List<CastLineEntity>): Int =
        cast.filter { !it.paid }.sumOf { it.amount }

    fun castOwedAll(gigs: List<GigEntity>, castByGig: Map<String, List<CastLineEntity>>): Int =
        gigs.sumOf { gig ->
            castByGig[gig.id]?.let { castOwedTotal(gig.id, it) } ?: 0
        }
}
