package server;

import database.Quiz;
import database.Total;

public class TeacherProtocol {
    Total total;

    public TeacherProtocol(Total total) {
        this.total = total;
    }

    // 선생으로부터 문제를 서버에 저장
    // 선생 / 프로토콜 / 문제 / 정답
    // 문제는 문제, 문제 정답의 형식
    public void getProblem(String request) {
        String[] strArr = request.split("/");
        total.quiz.add(new Quiz(total.quiz.size(), strArr[2], strArr[3]));
    }


    // 학생들의 점수를 선생에게 줌
    public String pushStudentScore() {

        return null;
    }




}
