package test;

import test.dao.VoteImpl;

public class VoteTest {
	public static void main(String[] args){
		VoteImpl vote = new VoteImpl();
		vote.addVote("bitcoin", "up", 5.1);
	}
}
