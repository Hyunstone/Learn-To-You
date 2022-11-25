package server;

import database.Quiz;
import database.Total;

public class TeacherProtocol {
    Total total;

    public TeacherProtocol(Total total) {
        this.total = total;
    }

    // 선생으로부터 문제를 서버에 저장
    public String getProblem(String request) {
        // 문제내용, 정답 넣어줘야 함
        
        //total.quiz.add(new Quiz(total.quiz.size(), ));

        return null;
    }


    // 학생들의 점수를 선생에게 줌
    public String pushStudentScore() {

        return null;
    }




}
