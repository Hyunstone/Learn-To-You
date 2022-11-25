package database;

public class ChallengeInfo {
	
	public String name;
	public int quizNumber;
	public String result;

	// 학생 답안 정보
	public ChallengeInfo(String name,int quizNumber,String result ) {
		this.name = name;
		this.quizNumber = quizNumber;
		this.result = result;
	}

	@Override
	public String toString() {
		return name + "/" + quizNumber + "/" + result ;
	}

}
