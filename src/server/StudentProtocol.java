package server;

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
    // 학생들로부터 답안 받아오기
    public String getAnswer() {

        return null;
    }

}
