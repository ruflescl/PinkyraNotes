package com.pinkyra.pinkyranotes.notesoverview.notedetail.notecarddetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinkyra.pinkyranotes.R;
import com.pinkyra.pinkyranotes.db.note.Note;
import com.pinkyra.pinkyranotes.notesoverview.notedetail.api.NoteItemListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for drawing notes in {@link com.pinkyra.pinkyranotes.notesoverview.NotesOverviewFragment}
 * with a 'card-like' layout
 */
public class NoteCardDetailAdapter extends RecyclerView.Adapter<NoteCardDetailAdapter.NoteCardDetailViewHolder> {

    private List<Note> noteList;
    private NoteItemListener itemListener;

    public NoteCardDetailAdapter(@NonNull NoteItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public NoteCardDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.content_notes_overview_card_detail, parent, false);

        return new NoteCardDetailViewHolder(noteView, itemListener);
    }

    @Override
    public void onBindViewHolder(NoteCardDetailViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.title);
        holder.content.setText(note.content);
        holder.color_indicator.setBackgroundResource(note.color_accent.getColorResource());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    Note getItem(int position) {
        return noteList.get(position);
    }

    public void replaceData(final List<Note> items) {
        if (noteList == null) {
            noteList = items;
            notifyItemRangeChanged(0, items.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return noteList.size();
                }

                @Override
                public int getNewListSize() {
                    return items.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return noteList.get(oldItemPosition).id == items.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Note oldNote = noteList.get(oldItemPosition);
                    Note newNote = noteList.get(newItemPosition);

                    return oldNote.equalsOther(newNote);
                }
            });

            noteList = items;
            result.dispatchUpdatesTo(this);
        }
    }

    /**
     * Card detail view holder used in {@link NoteCardDetailAdapter}
     */
    public class NoteCardDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.cent_view_color_indicator) public View color_indicator;
        @BindView(R.id.cnocd_txtv_title) public TextView title;
        @BindView(R.id.cnocd_txtv_content) public TextView content;

        NoteItemListener itemListener;

        public NoteCardDetailViewHolder(View itemView, NoteItemListener noteItemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.itemListener = noteItemListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Note note = getItem(position);
            itemListener.onNoteClick(note);
        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            Note note = getItem(position);
            itemListener.onNoteLongClick(note);
            return true;
        }
    }
}
