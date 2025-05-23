package io.issc.android_tutorial_kt.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM user_info")
    fun getAll(): List<UserInfo>

    @Query("SELECT * FROM user_info WHERE id IN (:userIds) LIMIT 1")
    fun loadAllByIds(userIds: IntArray): List<UserInfo>

    @Query("SELECT * FROM user_info WHERE username LIKE :first" + " LIMIT 1")
    fun findByName(first: String): UserInfo

    @Query("SELECT * FROM user_info WHERE id LIKE :id" + " LIMIT 1")
    fun findByID(id:Long):UserInfo

    @Insert
    fun save(user:UserInfo)

    @Update
    fun update(user:UserInfo)


    @Delete
    fun delete(user:UserInfo)

    @Query("DELETE FROM user_info WHERE id = :id")
    fun deleteById(id:Long)
}