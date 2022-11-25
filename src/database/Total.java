package database;

import java.util.Vector;

public class Total {
	public LoginInfo info = new LoginInfo();	
	public Vector<Quiz> quiz = new Vector<>();
	public Vector<ChallengeInfo> challengeInfo = new Vector<>();

	public Total() {
		info.add("교수","교오수" );
		info.add("학생1","일");
		info.add("학생2","이");
		info.add("학생3","삼");
		info.add("학생4","사");
		quiz.add(new Quiz(1, "거북이와 토끼중 소화불량인 동물은?","거북"));
	}

}