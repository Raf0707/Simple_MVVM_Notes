package raf.console.simplemvvmnotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import raf.console.simplemvvmnotes.domain.model.Note
import raf.console.simplemvvmnotes.domain.repo.NoteRepo

class NoteViewModel(private val noteRepo: NoteRepo): ViewModel() {

    val allNotes: LiveData<List<Note>> = noteRepo.allNotes

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepo.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepo.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepo.delete(note)
    }
}