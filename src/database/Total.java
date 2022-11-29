package database;

import java.util.ArrayList;

public class Total {
	public ArrayList<LoginInfo> info = new ArrayList<>();
	public ArrayList<Quiz> quiz = new ArrayList<>();
	public ArrayList<ChallengeInfo> challengeInfo = new ArrayList<>();

	public Total() {
		info.add(new LoginInfo("교수","11", 0));
		info.add(new LoginInfo("학생","1", 0));
		info.add(new LoginInfo("학생","2", 0));
		info.add(new LoginInfo("학생","3", 0));
		info.add(new LoginInfo("학생","4", 0));
		//quiz.add(new Quiz(1, "거북이와 토끼중 소화불량인 동물은?","거북"));
		//challengeInfo.add(new ChallengeInfo("1", 1, "거북"));
	}
}