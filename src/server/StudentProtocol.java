package server;

import database.ChallengeInfo;
import database.Quiz;
import database.Total;

import java.util.ArrayList;

public class StudentProtocol {

    Total total;

    public StudentProtocol(Total total) {
        this.total = total;
    }

    // 학생들에게 문제를 뿌림
    public ArrayList<Quiz> pushQuiz() {
        for (Quiz quiz: total.quiz) {
            System.out.println(quiz.number + " " + quiz.problem);
        }
        return total.quiz;
    }
    // 학생들로부터 답안 저장
    public void setAnswer(String request) {
        String[] strArr = request.split("/");
        String tempStr = strArr[3];
        int quizNum = Integer.parseInt(tempStr.trim());
        total.challengeInfo.add(new ChallengeInfo(strArr[2], quizNum, strArr[4]));
    }

}
