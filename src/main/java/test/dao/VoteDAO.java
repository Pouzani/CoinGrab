package test.dao;


public interface VoteDAO {
	public int getVoteCount(String vote,String coinId);
	public String getVoteAvg(String vote,String coinId);
	public void addVote(String voteType,String coinId, double votePercentage);
}
