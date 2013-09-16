package de.dhbw.die5gewinnt.model;

public class ServerFile {

	private String approval, setStatus, opponentMove, winner;
	
	public ServerFile() {
		this.setApproval("");
		this.setSetStatus("");
		this.setOpponentMove("");
		this.setWinner("");
	}
	
	public ServerFile(String approval, String setStatus, String opponentMove, String winner) {
		this.setApproval(approval);
		this.setSetStatus(setStatus);
		this.setOpponentMove(opponentMove);
		this.setWinner(winner);	
	}

	/* GETTER-Methods */
	public String getApproval() {
		return this.approval;
	}
	
	public String getSetStatus() {
		return this.setStatus;
	}
	
	public String getOpponentMove() {
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
	
	public void setOpponentMove(String opponentMove) {
		this.opponentMove = opponentMove;
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
}