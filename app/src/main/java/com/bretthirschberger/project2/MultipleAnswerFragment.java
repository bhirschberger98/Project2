package com.bretthirschberger.project2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MultipleAnswerFragment.OnOptionSelectedListener} interface
 * to handle interaction events.
 * Use the {@link MultipleAnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleAnswerFragment extends Fragment {
    private static final String OPTION1 = "option1";
    private static final String OPTION2 = "option2";
    private static final String OPTION3 = "option3";
    private static final String OPTION4 = "option4";
    private static final String OPTION5 = "option5";
    private static final String QUESTION = "question";
    private static final String CORRECT_ANSWER = "correctAnswer";

    private TextView mQuestion;

    private CheckBox mOption1;
    private CheckBox mOption2;
    private CheckBox mOption3;
    private CheckBox mOption4;
    private CheckBox mOption5;

    private OnOptionSelectedListener mListener;

    private Button mButton;

    public MultipleAnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MultipleAnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MultipleAnswerFragment newInstance(MultipleAnswerQuestion question) {
        MultipleAnswerFragment fragment = new MultipleAnswerFragment();
        Bundle args = new Bundle();
        args.putString(OPTION1, question.getOptions()[0]);
        args.putString(OPTION2, question.getOptions()[1]);
        args.putString(OPTION3, question.getOptions()[2]);
        args.putString(OPTION4, question.getOptions()[3]);
        args.putString(OPTION5, question.getOptions()[4]);
        args.putString(QUESTION, question.getQuestion());
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiple_answer, container, false);
        mOption1 = v.findViewById(R.id.check_option1);
        mOption2 = v.findViewById(R.id.check_option2);
        mOption3 = v.findViewById(R.id.check_option3);
        mOption4 = v.findViewById(R.id.check_option4);
        mOption5 = v.findViewById(R.id.check_option5);
        mQuestion = v.findViewById(R.id.question);
        mButton = v.findViewById(R.id.next);
        mButton.setOnClickListener(mListener::goToNext);
        if (getArguments() != null) {
            mOption1.setText(getArguments().getString(OPTION1));
            mOption2.setText(getArguments().getString(OPTION2));
            mOption3.setText(getArguments().getString(OPTION3));
            mOption4.setText(getArguments().getString(OPTION4));
            mOption5.setText(getArguments().getString(OPTION5));
            mQuestion.setText(getArguments().getString(QUESTION));
        }
        return v;
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
        void goToNext(View view);
    }
}
