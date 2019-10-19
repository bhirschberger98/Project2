package com.bretthirschberger.project2;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MultipleChoiceFragment.OnOptionSelectedListener} interface
 * to handle interaction events.
 * Use the {@link MultipleChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleChoiceFragment extends Fragment {
    private static final String OPTION1 = "option1";
    private static final String OPTION2 = "option2";
    private static final String OPTION3 = "option3";
    private static final String OPTION4 = "option4";
    private static final String QUESTION = "question";
    private static final String CORRECT_ANSWER = "correctAnswer";

    private OnOptionSelectedListener mListener;

    private RadioGroup mOptions;

    private RadioButton mOption1;
    private RadioButton mOption2;
    private RadioButton mOption3;
    private RadioButton mOption4;

    private TextView mQuestion;

    private Button mButton;

    private boolean mIsCorrect;

    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MultipleChoiceFragment.
     */
    public static MultipleChoiceFragment newInstance(MultipleChoiceQuestion question) {
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        args.putString(OPTION1, question.getOptions()[0]);
        args.putString(OPTION2, question.getOptions()[1]);
        args.putString(OPTION3, question.getOptions()[2]);
        args.putString(OPTION4, question.getOptions()[3]);
        args.putString(QUESTION, question.getQuestion());
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
        mOptions = v.findViewById(R.id.options);
        mQuestion = v.findViewById(R.id.question);
        mButton = v.findViewById(R.id.next);
        mButton.setEnabled(false);
        if (getArguments() != null) {
            mOption1.setText(getArguments().getString(OPTION1));
            mOption2.setText(getArguments().getString(OPTION2));
            mOption3.setText(getArguments().getString(OPTION3));
            mOption4.setText(getArguments().getString(OPTION4));
            mQuestion.setText(getArguments().getString(QUESTION));
            mOptions.setOnCheckedChangeListener((group, checkedId) -> {
                Log.i("ans", checkedId + "");
                Log.i("ans", getArguments().getInt(CORRECT_ANSWER) + "");
//                    getView().findViewById(R.id.option1).get
                if (checkedId == R.id.option1 && getArguments().getInt(CORRECT_ANSWER) == 1) {
                    mIsCorrect = true;
                } else if (checkedId == R.id.option2 && getArguments().getInt(CORRECT_ANSWER) == 2) {
                    mIsCorrect = true;
                } else if (checkedId == R.id.option3 && getArguments().getInt(CORRECT_ANSWER) == 3) {
                    mIsCorrect = true;
                } else if (checkedId == R.id.option4 && getArguments().getInt(CORRECT_ANSWER) == 4) {
                    mIsCorrect = true;
                } else {
                    mIsCorrect = false;
                }
                mButton.setEnabled(true);
            });
        }
        if (savedInstanceState != null) {
            Log.i("opt1", savedInstanceState.getBoolean("opt1") + "");
            Log.i("opt2", savedInstanceState.getBoolean("opt2") + "");
            Log.i("opt3", savedInstanceState.getBoolean("opt3") + "");
            Log.i("opt4", savedInstanceState.getBoolean("opt4") + "");
//            mOptions.check(R.id.option1);
//            mOption1.setChecked(savedInstanceState.getBoolean("opt1"));
//            mOption1.setActivated(true);
//            mOption2.setChecked(savedInstanceState.getBoolean("opt2"));
            mOption2.setChecked(true);
//            mOption3.setChecked(savedInstanceState.getBoolean("opt3"));
//            mOption4.setChecked(savedInstanceState.getBoolean("opt4"));
        }

        AlertDialog confirmDialog = new AlertDialog.Builder(v.getContext()).setMessage(getString(R.string.confirm_msg)).
                setCancelable(false).
                setPositiveButton(getString(R.string.yes), (dialog, which) -> mListener.goToNext(mIsCorrect)).
                setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel()).
                setTitle(getString(R.string.confirm_title)).create();

        mButton.setOnClickListener((view) -> confirmDialog.show());
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("opt1", mOption1.isChecked());
        outState.putBoolean("opt2", mOption2.isChecked());
        outState.putBoolean("opt3", mOption3.isChecked());
        outState.putBoolean("opt4", mOption4.isChecked());
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
        void goToNext(boolean isCorrect);
    }
}
