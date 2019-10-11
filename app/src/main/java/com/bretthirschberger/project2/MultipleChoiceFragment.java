package com.bretthirschberger.project2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MultipleChoiceFragment.OnOptionSelectedListener} interface
 * to handle interaction events.
 * Use the {@link MultipleChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleChoiceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUESTION1 = "question1";
    private static final String QUESTION2 = "question2";
    private static final String QUESTION3 = "question3";
    private static final String QUESTION4 = "question4";
    private static final String CORRECT_ANSWER = "correctAnswer";

    private OnOptionSelectedListener mListener;

    private RadioGroup mOptions;

    private RadioButton mOption1;
    private RadioButton mOption2;
    private RadioButton mOption3;
    private RadioButton mOption4;

    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MultipleChoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MultipleChoiceFragment newInstance(MultipleChoiceQuestion question) {
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION1, question.getOptions()[0]);
        args.putString(QUESTION2, question.getOptions()[1]);
        args.putString(QUESTION3, question.getOptions()[2]);
        args.putString(QUESTION4, question.getOptions()[3]);
        args.putInt(CORRECT_ANSWER, question.getCorrectAnswer());
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        mOption1 = v.findViewById(R.id.option1);
        mOption2 = v.findViewById(R.id.option2);
        mOption3 = v.findViewById(R.id.option3);
        mOption4 = v.findViewById(R.id.option4);
        mOptions =v.findViewById(R.id.options);
        if (getArguments() != null) {
            mOption1.setText(getArguments().getString(QUESTION1));
            mOption2.setText(getArguments().getString(QUESTION2));
            mOption3.setText(getArguments().getString(QUESTION3));
            mOption4.setText(getArguments().getString(QUESTION4));
            mOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Log.i("ans",checkedId+"");
                    Log.i("ans",getArguments().getInt(CORRECT_ANSWER)+"");
                    mListener.onOptionSelected(true);
                }
            });
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.OnOptionSelectedListener(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnOptionSelectedListener) {
            mListener = (OnOptionSelectedListener) context;
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
    public interface OnOptionSelectedListener {
        // TODO: Update argument type and name
        boolean onOptionSelected(Boolean isCorrect);
    }
}
