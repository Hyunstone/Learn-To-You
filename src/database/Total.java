package database;

import java.util.ArrayList;

public class Total {
	public ArrayList<LoginInfo> info = new ArrayList<>();
	public ArrayList<Quiz> quiz = new ArrayList<>();
	public ArrayList<ChallengeInfo> challengeInfo = new ArrayList<>();

	public Total() {
		info.add(new LoginInfo("교수","1", 0));
		info.add(new LoginInfo("학생1","딱", 0));
		info.add(new LoginInfo("학생2","2", 0));
		info.add(new LoginInfo("학생3","3", 0));
		info.add(new LoginInfo("학생4","4", 0));
		quiz.add(new Quiz(1, "거북이와 토끼중 소화불량인 동물은?","거북"));
		challengeInfo.add(new ChallengeInfo("개쇅히맨", 1, "힘드르용"));
	}
}