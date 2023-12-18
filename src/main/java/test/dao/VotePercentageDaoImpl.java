package test.dao;
import java.text.DecimalFormat;

import test.dao.DAOFactory;
import test.dao.VoteDaoImpl;

public class VotePercentageDaoImpl implements VotePercentageDAO {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private DAOFactory          daoFactory;
	
	public VotePercentageDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	public String getVotePercentageUp(String coinId) {
		VoteDaoImpl voteImpl = new VoteDaoImpl(daoFactory);
		int voteCountUp = voteImpl.getVoteCount("up", coinId);
		int voteCountDown = voteImpl.getVoteCount("down", coinId);
		double voteSum = voteCountUp+voteCountDown;
		double votePercentageUp = (voteCountUp/voteSum)*100;
		return df.format(votePercentageUp);
	}
	
	public String getVotePercentageDown(String coinId) {
		VoteDaoImpl voteImpl = new VoteDaoImpl(daoFactory);
		int voteCountUp = voteImpl.getVoteCount("up", coinId);
		int voteCountDown = voteImpl.getVoteCount("down", coinId);
		double voteSum = voteCountUp+voteCountDown;
		double votePercentageDown = (voteCountDown/voteSum)*100;
		return df.format(votePercentageDown);
	}
}
