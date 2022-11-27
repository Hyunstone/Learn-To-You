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

    public synchronized void giveScore(String request) {
        System.out.println("돌림");
        String[] strArr = request.split("/");
        String tempStr = strArr[3];
        String sName = strArr[2];//학생 리스트값 변경을 위한 이름 저장
        int StudentPlace = -1; // total에 접근할때 이름과 맞는 위치 저장

        int k = 0;
        for (LoginInfo loginInfo : total.info) {
            if (loginInfo.name.equals(strArr[2])) {
                System.out.println(sName + " " + k);
                StudentPlace = k;
                break;
            }
            k++;
        }

        //System.out.println("기존점수: " + total.info.get(k).point);
        int givePoint = total.info.get(k).point;
        int quizNum = Integer.parseInt(tempStr.trim());

        for (int a = 0; a < total.quiz.size(); a++) { //퀴즈 순회
            if (quizNum == total.quiz.get(a).number) { //문제 번호 찾기
                if (strArr[4].equals(total.quiz.get(a).answer)) {//정답 확인
                    total.info.set(StudentPlace, new LoginInfo("학생", sName, givePoint + 1));
                    //System.out.println(total.info.get(StudentPlace).point);
                    //System.out.println("점수 올라감");
                }
                else{
                    total.info.set(StudentPlace, new LoginInfo("학생", sName, givePoint));
                    //System.out.println(total.info.get(StudentPlace).point);
                    //System.out.println("점수 그대로");
                }
                break;
            }
        }
    }
}
