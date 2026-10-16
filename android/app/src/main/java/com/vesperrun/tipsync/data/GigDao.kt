package com.vesperrun.tipsync.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GigDao {
    @Query("SELECT * FROM gigs ORDER BY callTimeIso ASC")
    fun observeGigs(): Flow<List<GigEntity>>

    @Query(
        """
        SELECT * FROM gigs
        WHERE status IN ('Inquiry', 'Negotiating')
        ORDER BY callTimeIso ASC
        """,
    )
    fun observeInbox(): Flow<List<GigEntity>>

    @Query("SELECT * FROM cast_lines WHERE gigId = :gigId")
    fun observeCastForGig(gigId: String): Flow<List<CastLineEntity>>

    @Query("SELECT * FROM gigs WHERE id = :id LIMIT 1")
    suspend fun gigById(id: String): GigEntity?

    @Query("SELECT COUNT(*) FROM gigs")
    suspend fun gigCount(): Int

    @Query("SELECT COALESCE(SUM(amount), 0) FROM cast_lines WHERE paid = 0")
    fun observeCastOwedTotal(): Flow<Int>

    @Query(
        """
        SELECT * FROM gigs
        WHERE guarantee > 0 AND guaranteeReceived = 0
        ORDER BY callTimeIso DESC
        """,
    )
    fun observeGuaranteesUnreceived(): Flow<List<GigEntity>>

    @Query(
        """
        SELECT c.* FROM cast_lines c
        INNER JOIN gigs g ON g.id = c.gigId
        WHERE c.paid = 0
        ORDER BY g.callTimeIso DESC
        """,
    )
    fun observeCastLinesOwed(): Flow<List<CastLineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGigs(gigs: List<GigEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(lines: List<CastLineEntity>)

    @Update
    suspend fun updateGig(gig: GigEntity)

    @Update
    suspend fun updateCastLine(line: CastLineEntity)
}
