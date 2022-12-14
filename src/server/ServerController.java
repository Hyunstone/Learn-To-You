package server;

import database.ChallengeInfo;
import database.LoginInfo;
import database.Quiz;
import database.Total;
import java.util.ArrayList;

public class ServerController {
    // 여기에 total을 추가는 했는데 없앨수도 O
    Total total;
    TeacherProtocol teacherProtocol;
    StudentProtocol studentProtocol;

    public ServerController(Total total) {
        this.total = total;
        teacherProtocol = new TeacherProtocol(total);
        studentProtocol = new StudentProtocol(total);
    }

    // request 받은 내용을 분석 후 알맞은 행동을 선택하도록 리턴
    public String response(String request) {
        String act = null;
        // 선생 프로토콜
        if (isTeacherProtocol(request)) {
            // 문제 저장
            if (callProtocol(request).equals("0")) {
                teacherProtocol.setQuiz(request);
                return "문제 입력 완료\r\n";
            }
            // 문제 불러오기
            else if (callProtocol(request).equals("1")) {
                ArrayList<Quiz> quiz = teacherProtocol.pushQuiz();
                //System.out.println(quiz);
                return quiz + "\r\n";
            }
            // 학생들 답안 불러오기
            else if (callProtocol(request).equals("2")) {
                ArrayList<ChallengeInfo> challengeInfos = teacherProtocol.pushStudentAnswer();
                return challengeInfos + "\r\n";
            }
            //학생들 성적 확인
            else if(callProtocol(request).equals("3")){
                ArrayList<LoginInfo> loginInfos = teacherProtocol.pushPoint();
                return loginInfos + "\r\n";
            }
        }
        // 학생 프로토콜
        else {
            // 문제 가져오기
            if (callProtocol(request).equals("0")) {
                ArrayList<Quiz> quiz = studentProtocol.pushQuiz();
                return quiz + "\r\n";
            }
            // 정답 제출하기
            else if (callProtocol(request).equals("1")) {
                studentProtocol.setAnswer(request);
                studentProtocol.giveScore(request);
                return "정답 제출 완료\r\n";
            }
            //점수 가져오기
            else if (callProtocol(request).equals("2")) {
                String[] strArr = request.split("/");

                for (LoginInfo info: total.info) {
                    if (info.name.equals(strArr[2])) {
                        return info.point + "\r\n";
                    }
                }
            }
        }
        return act;
    }

    // 선생프로토콜인지 확인
    // 0이면 true 리턴(선생프로토콜 호출), 0이 아니면(1이면) false 리턴(학생프로토콜 호출)
    private boolean isTeacherProtocol(String str) {
        String[] strArr = str.split("/");
        return strArr[0].equals("0");
    }

    // 프로토콜 내용 전달
    // request에 있는 프로토콜을 리턴
    private String callProtocol(String request) {
        String[] strArr = request.split("/");
        return strArr[1];
    }
}
