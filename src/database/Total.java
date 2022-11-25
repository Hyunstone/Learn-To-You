package database;

import java.util.ArrayList;

public class Total {
	public LoginInfo info = new LoginInfo();	
	public ArrayList<Quiz> quiz = new ArrayList<>();
	public ArrayList<ChallengeInfo> challengeInfo = new ArrayList<>();

	public Total() {
		info.add("교수","1" );
		info.add("학생1","딱");
		info.add("학생2","2");
		info.add("학생3","3");
		info.add("학생4","4");
		quiz.add(new Quiz(1, "거북이와 토끼중 소화불량인 동물은?","거북"));
		challengeInfo.add(new ChallengeInfo("개쇅히맨", 1, "힘드르용"));
	}
}