package com.example.myapp015amynotehub.database

import androidx.room.*
import com.example.myapp015amynotehub.models.Note
import com.example.myapp015amynotehub.models.NoteTagCrossRef
import com.example.myapp015amynotehub.models.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteTagDao {
    @Insert
    suspend fun insert(noteTagCrossRef: NoteTagCrossRef)

    @Delete
    suspend fun deleteNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef)

    @Transaction
    @Query("SELECT * FROM tag_table INNER JOIN note_tag_cross_ref ON tag_table.id = note_tag_cross_ref.tagId WHERE note_tag_cross_ref.noteId = :noteId")
    fun getTagsForNote(noteId: Int): Flow<List<Tag>>

    @Transaction
    @Query("SELECT * FROM note_table INNER JOIN note_tag_cross_ref ON note_table.id = note_tag_cross_ref.noteId WHERE note_tag_cross_ref.tagId = :tagId")
    fun getNotesForTag(tagId: Int): Flow<List<Note>>

    @Transaction
    @Query("SELECT * FROM tag_table INNER JOIN note_tag_cross_ref ON tag_table.id = note_tag_cross_ref.tagId WHERE note_tag_cross_ref.noteId = :noteId")
    fun getTagsForNoteSync(noteId: Int): List<Tag>

}