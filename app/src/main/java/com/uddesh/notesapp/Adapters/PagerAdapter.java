package com.uddesh.notesapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.uddesh.notesapp.Fragments.NotesFragment;
import com.uddesh.notesapp.Fragments.TaskFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = NotesFragment.newInstance();
                break;
            case 1:
                fragment = TaskFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
