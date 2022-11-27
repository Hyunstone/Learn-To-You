package database;

public class Quiz {
	public int number;
	public String problem;
	public String answer;
	
	public Quiz(int number, String problem ,String answer) {
		this.number=number;
		this.problem=problem;
		this.answer=answer;
	}
	@Override
	public String toString() {
		return number + "/" + problem;
	}
}
