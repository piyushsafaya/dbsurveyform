package municipality.dubai.com.welcometodubaimunicipalityevent;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import municipality.dubai.com.welcometodubaimunicipalityevent.Models.Question;

import static municipality.dubai.com.welcometodubaimunicipalityevent.QuizActivity.correctAnswerReceived;

/**
 * Created by grigorz on 9/19/17.
 */

class QuizListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Question currentQuestion;
    private Context context;
    private boolean showRightAnswer;
    private OnAnswerReceived callback;
    public QuizListAdapter(Question currentQuestion, Context context) {
        this.currentQuestion = currentQuestion;
        showRightAnswer = false;
        this.context = context;
    }

    public QuizListAdapter(Question currentQuestion, Context context, OnAnswerReceived callback) {
        this.currentQuestion = currentQuestion;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        return new QuizListAdapter.ViewHolder((LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.quiz_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final QuizListAdapter.ViewHolder vh = (QuizListAdapter.ViewHolder) holder;
        vh.text.setText(currentQuestion.getPossibleAnswers()[position]);
        if (!showRightAnswer) {
            vh.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == currentQuestion.getRightAnswerPosition()) {
                        if (!showRightAnswer){
                            vh.text.setBackgroundColor(ContextCompat.getColor(context, R.color.right_answer));
                            correctAnswerReceived();
                            callback.activateNextButton(true);
                        }
                    } else {
                        if (!showRightAnswer){
                            vh.text.setBackgroundColor(ContextCompat.getColor(context, R.color.wrong_answer));
                            callback.activateNextButton(false);
                        }
                    }

                    showRightAnswer = true;
                    notifyDataSetChanged();
                }
            });
        } else {
            if (position == currentQuestion.getRightAnswerPosition()) {
                vh.text.setBackgroundColor(ContextCompat.getColor(context, R.color.right_answer));
            }
        }


    }

    @Override
    public int getItemCount() {
        return currentQuestion.getAnswersCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.possible_answer);
            item = (LinearLayout) itemView.findViewById(R.id.list_item);
        }
    }


}
