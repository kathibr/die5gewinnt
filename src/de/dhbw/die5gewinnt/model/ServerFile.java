package de.dhbw.die5gewinnt.model;

public class ServerFile {

	private String approval, setStatus, winner;
	private int opponentMove;
	
	public ServerFile() {

	}
	
	public ServerFile(String approval, String setStatus, int opponentMove, String winner) {
		this.setApproval(approval);
		this.setSetStatus(setStatus);
		this.setOpponentMove(opponentMove);
		this.setWinner(winner);	
	}

	/* GETTER-Methods */
	public boolean getApproval() {
		if(this.approval.equals("true"))
			return true;
		else
			return false;
	}
	
	public String getSetStatus() {
		return this.setStatus;
	}
	
	public int getOpponentMove() {
		return this.opponentMove;
	}
	
	public String getWinner() {
		return this.winner;
	}
	
	
	/* SETTER-Methods */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public void setSetStatus(String setStatus) {
		this.setStatus = setStatus;
	}
	
	public void setOpponentMove(int opponentMove) {
		this.opponentMove = opponentMove;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}