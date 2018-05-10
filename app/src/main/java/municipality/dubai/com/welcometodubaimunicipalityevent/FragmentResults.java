package municipality.dubai.com.welcometodubaimunicipalityevent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import static municipality.dubai.com.welcometodubaimunicipalityevent.QuizActivity.getCorrectAnswersCount;
import static municipality.dubai.com.welcometodubaimunicipalityevent.QuizActivity.getCurrentQuestionsCount;

/**
 * Created by grigorz on 9/21/17.
 */

public class FragmentResults extends Fragment implements
        UpdateableFragment {

    ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_quiz_results, container, false);

        TextView finalResult = rootView.findViewById(R.id.final_result);
        finalResult.setText(getResources().getString(R.string.you_got) +
                " " + String.valueOf(getCorrectAnswersCount()) +
                " " + getResources().getString(R.string.of) +
                " " + String.valueOf(getCurrentQuestionsCount()) +
                " " + getResources().getString(R.string.right) +
                "\n" + getResources().getString(R.string.awesome)

        );

        return rootView;
    }

    protected static void setResultFromLastQuestion(int correctAnswersCount) {

    }


    @Override
    public void update(int correctAnswersCount) {
        Log.e("TAG", "on update");
        TextView finalResult = rootView.findViewById(R.id.final_result);
        finalResult.setText(getResources().getString(R.string.you_got) +
                " " + String.valueOf(getCorrectAnswersCount()) +
                " " + getResources().getString(R.string.of) +
                " " + String.valueOf(getCurrentQuestionsCount()) +
                " " + getResources().getString(R.string.right) +
                "\n" + getResources().getString(R.string.awesome)

        );
    }
}
