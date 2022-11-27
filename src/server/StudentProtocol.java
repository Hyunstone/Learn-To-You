package server;

import database.ChallengeInfo;
import database.LoginInfo;
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
    public ArrayList<LoginInfo> returnPoint(String request){
        String[] strArr = request.split("/");
        String sName = strArr[2];
        return total.info;
    }
//    public synchronized void giveScore(String request) {
//        String[] strArr = request.split("/");
//        String tempStr = strArr[3];
//        int quizNum = Integer.parseInt(tempStr.trim());
//        for(int a = 0; a < total.quiz.size(); a++){//퀴즈 순회
//            if(quizNum == total.quiz.get(a).number ){//문제 번호 찾기
//                String sName = strArr[2];//학생 리스트값 변경을 위한 이름 저장
//                int givePoint = total.info.get(a).point + 1;
//                if(strArr[4] == total.quiz.get(a).answer){//정답 확인
//                    total.info.set(Integer.parseInt(strArr[0]), new LoginInfo("학생", sName, givePoint));
//                }
//                else{
//                    total.info.set(Integer.parseInt(strArr[0]), new LoginInfo("학생", sName, givePoint));
//                }
//            }
//            else{
//                continue;
//            }
//        }
//    }
}
