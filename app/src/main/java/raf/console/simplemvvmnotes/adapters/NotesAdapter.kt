package raf.console.simplemvvmnotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import raf.console.simplemvvmnotes.R
import raf.console.simplemvvmnotes.databinding.CardNoteBinding
import raf.console.simplemvvmnotes.domain.model.Note

class NotesAdapter(
    private val onNoteClicked: (Note) -> Unit
): ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    private var notesList = mutableListOf<Note>()

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: CardNoteBinding =
            CardNoteBinding.bind(itemView)

        fun bindNote(note: Note, onNoteClicked: (Note) -> Unit) {
            binding.noteTitle.text = note.title
            binding.noteContent.text = note.content

            binding.noteCard.setOnClickListener {
                onNoteClicked(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bindNote(note, onNoteClicked)
    }

    fun setNotes(newNotes: List<Note>) {
        notesList.clear()
        notesList.addAll(newNotes)
        notifyDataSetChanged() // Полное обновление (менее эффективно)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}