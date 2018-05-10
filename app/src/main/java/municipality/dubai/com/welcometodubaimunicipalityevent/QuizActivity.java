package municipality.dubai.com.welcometodubaimunicipalityevent;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import municipality.dubai.com.welcometodubaimunicipalityevent.Models.PredefinedQuestions;

import static municipality.dubai.com.welcometodubaimunicipalityevent.Constants.POSITION_KEY;
import static municipality.dubai.com.welcometodubaimunicipalityevent.Constants.PREDEFINE_QUESTIONS_KEY;

public class QuizActivity extends FragmentActivity {

    private static int correctAnswersCount, currentQuestionsCount;
    private PredefinedQuestions predefinedQuestions;
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);


        predefinedQuestions = new PredefinedQuestions();
        currentQuestionsCount = predefinedQuestions.getSize();
        currentPosition = 0;
        showFragmentByPosition(currentPosition);


    }


    private void showFragmentByPosition (int position) {
        FragmentQuiz fragment = new FragmentQuiz();
        String question = new Gson().toJson(predefinedQuestions);
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION_KEY, position);
        bundle.putString(PREDEFINE_QUESTIONS_KEY, question);
        fragment.setArguments(bundle);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.viewPager, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private void showResultFragment() {
        FragmentResults fragment = new FragmentResults();

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.viewPager, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    static public void correctAnswerReceived() {
        correctAnswersCount++;
        Log.e("TAG", "correct answer received " + correctAnswersCount);
    }

    static public int getCorrectAnswersCount() {
        Log.e("TAG", "show results " + correctAnswersCount);
        return correctAnswersCount;
    }


    static public int getCurrentQuestionsCount() {
        return currentQuestionsCount;
    }


    public void onNextClicked(View view) {
        if (currentPosition < predefinedQuestions.getSize() - 1) {
            // If not last question show next one
            showFragmentByPosition(++currentPosition);
        } else {
            // Otherwise, select the previous step.
            showResultFragment();
        }
    }

    public void onExitClicked(View view) {
        correctAnswersCount = 0;
        // open welcome screen
        Intent welcomeScreen = new Intent(QuizActivity.this, ActivityWelcome.class);
        startActivity(welcomeScreen);
    }

    public void onTryAgainClicked(View view) {
        correctAnswersCount = 0; // reset the result
        currentPosition = 0;
        showFragmentByPosition(currentPosition); // start from first question
    }

    @Override
    public void onBackPressed() { // do nothing

    }
}
