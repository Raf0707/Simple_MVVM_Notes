package raf.console.simplemvvmnotes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import raf.console.simplemvvmnotes.adapters.NotesAdapter
import raf.console.simplemvvmnotes.databinding.ActivityMainBinding
import raf.console.simplemvvmnotes.domain.database.NoteDatabase
import raf.console.simplemvvmnotes.domain.model.Note
import raf.console.simplemvvmnotes.domain.repo.NoteRepo
import raf.console.simplemvvmnotes.viewmodel.NoteViewModel
import raf.console.simplemvvmnotes.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(NoteRepo(NoteDatabase.getDatabase(application).noteDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализируем адаптер с обработчиком нажатий на элементы списка
        notesAdapter = NotesAdapter { note ->
            // Для простоты можно добавить обработчик нажатий на элементы списка
            Toast.makeText(this, "Заметка: ${note.title}", Toast.LENGTH_SHORT).show()
        }

        // Устанавливаем RecyclerView
        binding.recycleNotes.layoutManager = LinearLayoutManager(this)
        binding.recycleNotes.adapter = notesAdapter

        // Наблюдаем за данными заметок
        noteViewModel.allNotes.observe(this) { notes ->
            // Отображаем или скрываем сообщение "Нет заметок"
            binding.noRes.visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
            notesAdapter.submitList(notes) // Обновляем список заметок
        }

        // Обработчик нажатия на FAB для добавления новой заметки
        binding.fabAddNote.setOnClickListener {
            // Добавляем новую заметку через ViewModel
            val newNote = Note(
                id = 0, // id будет автоматически сгенерирован
                title = "Новая заметка",
                content = "Содержимое заметки"
            )
            noteViewModel.addNote(newNote)
        }
    }
}
