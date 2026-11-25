package com.example.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "saved_pairings",
    indices = [
        Index(value = ["pairingId"], unique = true),
        Index(value = ["isFavorite"]),
        Index(value = ["updatedAt"]),
    ],
)
data class SavedPairingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val pairingId: String,
    val cigarId: String,
    val beverageId: String,
    val cigarName: String,
    val beverageName: String,
    val matchScore: Int,
    val pairingStyle: String,
    val strategyName: String,
    val personalNotes: String,
    val occasionTag: String,
    val isFavorite: Boolean,
    val timesEnjoyed: Int,
    val isCuratedSeed: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
)
