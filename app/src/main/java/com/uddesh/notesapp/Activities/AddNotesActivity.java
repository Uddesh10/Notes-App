package com.uddesh.notesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.uddesh.notesapp.Database.NotesEntity;
import com.uddesh.notesapp.R;
import com.uddesh.notesapp.ViewModel.NotesViewModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNotesActivity extends AppCompatActivity {
    ConstraintLayout addNotesLayout;
    ImageView backBtn , saveBtn , deleteBtn;
    EditText noteTitle , noteDescription;
    String backgroundColor = "#ecf0f1" , noteTitleText , noteDescriptionText;
    Date date;
    int noteId;
    SimpleDateFormat simpleDateFormat;
    boolean isUpdating = false;
    NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Bundle bundle = getIntent().getExtras();
        try {
            isUpdating = bundle.getBoolean("isUpdating");
        }catch (Exception e){}
        addNotesLayout = findViewById(R.id.add_notes_layout);
        backBtn = findViewById(R.id.back_btn);
        saveBtn = findViewById(R.id.save_note_btn);
        deleteBtn = findViewById(R.id.delete_note_btn);
        noteTitle = findViewById(R.id.note_title);
        noteDescription = findViewById(R.id.note_description);
        notesViewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NotesViewModel.class);
        if(isUpdating)
        {
            backgroundColor = bundle.getString("noteBackgroundColor");
            noteTitleText = bundle.getString("noteTitle");
            noteDescriptionText = bundle.getString("noteDescription");
            noteId = bundle.getInt("noteId");
            addNotesLayout.setBackgroundColor(Color.parseColor(backgroundColor));
            noteTitle.setText(noteTitleText);
            noteDescription.setText(noteDescriptionText);
        }
        date = new Date();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });
    }

    public void changeBackgroundColor(View view)
    {
        switch (view.getId())
        {
            case R.id.white_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#ecf0f1"));
                backgroundColor = "#ecf0f1";
                break;
            case R.id.orange_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#E67E22"));
                backgroundColor = "#E67E22";
                break;
            case R.id.emerald_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#2ECC71"));
                backgroundColor = "#2ECC71";
                break;
            case R.id.blue_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#3498DB"));
                backgroundColor = "#3498DB";
                break;
            case R.id.yellow_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#F1C40F"));
                backgroundColor = "#F1C40F";
                break;
            case R.id.green_background:
                addNotesLayout.setBackgroundColor(Color.parseColor("#1abc9c"));
                backgroundColor = "#1abc9c";
                break;

        }
    }

    private void saveNote()
    {
        noteTitleText = noteTitle.getText().toString();
        noteDescriptionText = noteDescription.getText().toString();
        simpleDateFormat= new SimpleDateFormat("dd LLL yyyy" , Locale.ENGLISH);
        final String noteDateText = simpleDateFormat.format(date);
        NotesEntity note = new NotesEntity(noteId , noteTitleText , noteDescriptionText , backgroundColor , noteDateText);
        if(isUpdating) {
            if(!noteTitleText.equals("") && !noteDescriptionText.equals("")) {
                notesViewModel.updateNote(note);
                makeToast("Note Updated" , Toast.LENGTH_SHORT);
                finish();

            }
            else
            {
                makeToast("Note not updated title or description empty" , Toast.LENGTH_LONG);
            }
        }
        else
        {
            if (!noteTitleText.equals("") && !noteDescriptionText.equals("")) {
                notesViewModel.insertNote(note);
                makeToast("Note Saved" , Toast.LENGTH_SHORT);
                finish();
            } else {
                makeToast("Note not saved title or description empty" , Toast.LENGTH_LONG);
            }
        }
    }

    private void deleteNote()
    {
        if(isUpdating)
        {
            NotesEntity note = new NotesEntity(noteId);
           notesViewModel.deleteNote(note);
        }
        makeToast("Note deleted" , Toast.LENGTH_SHORT);
        finish();
    }

    private void makeToast(String msg , int length)
    {
        Toast.makeText(getApplicationContext() , msg , length).show();
    }

}