package raf.console.simplemvvmnotes.domain.repo

import androidx.lifecycle.LiveData
import raf.console.simplemvvmnotes.domain.dao.NoteDao
import raf.console.simplemvvmnotes.domain.model.Note

class NoteRepo(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}