package com.pinkyra.pinkyranotes.noteeditor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Note editor fragment for choosing the color of the note
 */
public class NoteEditorColorAccentPickerFragment extends Fragment {
    // the fragment initialization parameters
    private static final String ARG_INIT_COLOR = "ARG_INIT_COLOR";

    private Integer initialColor;

    private OnFragmentInteractionListener mListener;

    public NoteEditorColorAccentPickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param initialColorId Color ID to be displayed.
     * @return A new instance of fragment NoteEditorColorAccentPickerFragment.
     */
    public static NoteEditorColorAccentPickerFragment newInstance(@NonNull Integer initialColorId) {
        NoteEditorColorAccentPickerFragment fragment = new NoteEditorColorAccentPickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INIT_COLOR, initialColorId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initialColor = getArguments().getInt(ARG_INIT_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        return textView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
