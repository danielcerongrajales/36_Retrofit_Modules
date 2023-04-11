package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.Converters
import com.example.data.database.dao.MovieDao
import com.example.data.database.dao.MovieDetailsDao
import com.example.data.database.dao.RemoteKeyDao
import com.example.data.database.entities.MovieDetailsEntity
import com.example.data.database.entities.MovieEntity
import com.example.data.database.entities.RemoteKey


@Database(
    entities = [
        MovieEntity::class,
        RemoteKey::class,
        MovieDetailsEntity::class
    ], version = 12, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

}