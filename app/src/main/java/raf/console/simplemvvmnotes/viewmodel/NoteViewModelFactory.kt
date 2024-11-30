package raf.console.simplemvvmnotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import raf.console.simplemvvmnotes.domain.repo.NoteRepo

class NoteViewModelFactory(private val noteRepo: NoteRepo) : ViewModelProvider.Factory {

    // Переопределяем метод для создания ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Проверяем, что модель имеет нужный тип
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            // Создаём экземпляр NoteViewModel, передавая репозиторий
            return NoteViewModel(noteRepo) as T
        }
        // Если ViewModel другого типа, выбрасываем исключение
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
