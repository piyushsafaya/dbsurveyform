package municipality.dubai.com.welcometodubaimunicipalityevent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import municipality.dubai.com.welcometodubaimunicipalityevent.Models.PredefinedQuestions;
import municipality.dubai.com.welcometodubaimunicipalityevent.Models.Question;

import static municipality.dubai.com.welcometodubaimunicipalityevent.Constants.POSITION_KEY;
import static municipality.dubai.com.welcometodubaimunicipalityevent.Constants.PREDEFINE_QUESTIONS_KEY;
import static municipality.dubai.com.welcometodubaimunicipalityevent.QuizActivity.getCorrectAnswersCount;


/**
 * Created by grigorz on 9/19/17.
 */

public class FragmentQuiz extends Fragment implements OnAnswerReceived{

    private Question currentQuestion;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView itemsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Context context;
    private ViewGroup rootView;
    private OnAnswerReceived callback;
    private TextView result;
    private int currentPageNumber, questionsSize;
    private UpdateableFragment updateFragment;

   /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof UpdateableFragment) {
            updateFragment = (UpdateableFragment) activity;
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_quiz, container, false);
        this.rootView = rootView;
        callback = this;
        context = getContext();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int position = bundle.getInt(POSITION_KEY, 0);
            currentPageNumber = position;
            String gsonString = bundle.getString(PREDEFINE_QUESTIONS_KEY, null);
            Gson gson = new Gson();
            PredefinedQuestions questions = gson.fromJson(gsonString, PredefinedQuestions.class);
            questionsSize = questions.getSize();
            currentQuestion = questions.getCurrentQuestion(position);
        }


        if (getActivity() instanceof UpdateableFragment) {
            updateFragment = (UpdateableFragment) getActivity();
        }

        TextView question = (TextView) rootView.findViewById(R.id.question);
        question.setText(currentQuestion.getQuestion());
        result = (TextView) rootView.findViewById(R.id.result);

        itemsRecyclerView = (RecyclerView) rootView.findViewById(R.id.question_possible_answers);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        itemsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new QuizListAdapter(currentQuestion, getContext(), callback);
        itemsRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    @Override
    public void activateNextButton(boolean answerCorrect) {
        Button next = (Button) rootView.findViewById(R.id.next_button);
        next.setClickable(true);
        next.setBackground(ContextCompat.getDrawable(context, R.drawable.welcome_button));
        if (answerCorrect) {
            result.setTextColor(ContextCompat.getColor(context, R.color.right_answer_text));
            result.setText(getResources().getString(R.string.correct_answer));

        } else {
            result.setTextColor(ContextCompat.getColor(context, R.color.wrong_answer_text));
            result.setText(getResources().getString(R.string.wrong_answer));
        }
        result.setVisibility(View.VISIBLE);
        if (currentPageNumber == questionsSize) {
           // setResultFromLastQuestion(getCorrectAnswersCount());
            updateFragment.update(getCorrectAnswersCount());
        }
    }
}
