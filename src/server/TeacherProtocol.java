package server;

import database.ChallengeInfo;
import database.Quiz;
import database.Total;
import java.util.ArrayList;

public class TeacherProtocol {
    Total total;

    public TeacherProtocol(Total total) {
        this.total = total;
    }

    // 선생으로부터 문제를 서버에 저장
    // 선생 / 프로토콜 / 문제 / 정답
    // 문제는 문제, 문제 정답의 형식
    public void setQuiz(String request) {
        String[] strArr = request.split("/");
        total.quiz.add(new Quiz(total.quiz.size() + 1, strArr[2], strArr[3]));
    }
    // 선생이 문제를 가져올때, 문제수 확인시
    public ArrayList<Quiz> pushQuiz() {
        for (Quiz quiz: total.quiz) {
            System.out.println(quiz.number + " " + quiz.problem);
        }
        return total.quiz;
    }

    // 서버에 있는 학생들의 답안을 선생에게 줌
    public ArrayList<ChallengeInfo> pushStudentAnswer() {
        for (ChallengeInfo challengeInfo: total.challengeInfo) {
            System.out.println(challengeInfo.name + " " + challengeInfo.quizNumber + " " + challengeInfo.result);
        }
        return total.challengeInfo;
    }
}
