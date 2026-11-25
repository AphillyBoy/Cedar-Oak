package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        PairingReviewEntity::class,
        AudioTranscriptionEntity::class,
        SavedPairingEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pairingReviewDao(): PairingReviewDao
    abstract fun audioTranscriptionDao(): AudioTranscriptionDao
    abstract fun savedPairingDao(): SavedPairingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `audio_transcriptions` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `title` TEXT NOT NULL,
                        `sourceLabel` TEXT NOT NULL,
                        `durationSeconds` INTEGER NOT NULL,
                        `styleLabel` TEXT NOT NULL,
                        `transcriptText` TEXT NOT NULL,
                        `createdAt` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `saved_pairings` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `pairingId` TEXT NOT NULL,
                        `cigarId` TEXT NOT NULL,
                        `beverageId` TEXT NOT NULL,
                        `cigarName` TEXT NOT NULL,
                        `beverageName` TEXT NOT NULL,
                        `matchScore` INTEGER NOT NULL,
                        `pairingStyle` TEXT NOT NULL,
                        `strategyName` TEXT NOT NULL,
                        `personalNotes` TEXT NOT NULL,
                        `occasionTag` TEXT NOT NULL,
                        `isFavorite` INTEGER NOT NULL,
                        `timesEnjoyed` INTEGER NOT NULL,
                        `isCuratedSeed` INTEGER NOT NULL,
                        `createdAt` INTEGER NOT NULL,
                        `updatedAt` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS `index_saved_pairings_pairingId` ON `saved_pairings` (`pairingId`)"
                )
                db.execSQL(
                    "CREATE INDEX IF NOT EXISTS `index_saved_pairings_isFavorite` ON `saved_pairings` (`isFavorite`)"
                )
                db.execSQL(
                    "CREATE INDEX IF NOT EXISTS `index_saved_pairings_updatedAt` ON `saved_pairings` (`updatedAt`)"
                )
            }
        }

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cedar_and_oak.db",
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
