package server;

import database.Total;

public class ServerController {
    // 여기에 total을 추가는 했는데 없앨수도 O
    Total total;
    TeacherProtocol teacherProtocol;
    StudentProtocol studentProtocol;

    public ServerController(Total total) {
        this.total = total;
        teacherProtocol = new TeacherProtocol(total);
        studentProtocol = new StudentProtocol();
    }

    // request 받은 내용을 분석 후 알맞은 행동을 선택하도록 리턴
    public String response(String request) {
        String act = null;

        // 선생 프로토콜 호출
        if (isTeacherProtocol(request)) {
            // 문제 저장
            if (callProtocol(request) == 0) {
                teacherProtocol.getProblem(request);
            }
            // 학샏을 점수 불러오기
            else if (callProtocol(request) == 1) {
                teacherProtocol.pushStudentScore();
            }
        }
        else {
            if (callProtocol(request) == 0) {

            }
            else if (callProtocol(request) == 1) {

            }
        }

        return act;
    }

    // 선생프로토콜인지 확인
    // 0이면 true 리턴(선생프로토콜 호출), 0이 아니면(1이면) false 리턴(학생프로토콜 호출)
    private boolean isTeacherProtocol(String str) {
        String[] strArr = str.split("/");
        if (strArr[0] == "0") {
            return true;
        }
        return false;
    }

    // 프로토콜 내용 전달
    // request에 있는 프로토콜을 리턴
    private int callProtocol(String request) {
        String[] strArr = request.split("/");
        return Integer.parseInt(strArr[1]);
    }
    // 학생의 답안으로 채점
    private Boolean gradeProblem() {

        return null;
    }

    // gradeProblem을 바탕으로 점수 반영
    private void reflectScore() {

    }

}
