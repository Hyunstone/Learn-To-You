package database;

public class ChallengeInfo {
	
	public String name;
	public int quizNumber;
	public String result;
	
	public ChallengeInfo(String name,int quizNumber,String result ) {
		this.name =name;
		this.quizNumber =quizNumber;
		this.result =result;
	}

	@Override
	public String toString() {
		return "학생 " + name + "이 퀴즈문항 " + quizNumber + "번 " + result ;
	}

}
