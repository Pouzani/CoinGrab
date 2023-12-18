package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class VoteDaoImpl implements VoteDAO {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private DAOFactory daoFactory;
	
	public VoteDaoImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public int getVoteCount(String vote,String coinId) {
		int voteCount = 0;
		try {
			Connection conn = daoFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(vote) FROM vote WHERE vote=? AND Id_coin=? ");
			ps.setString(1, vote);
			ps.setString(2, coinId);
			ResultSet rs = ps.executeQuery();
			rs.first();
			voteCount = rs.getInt("COUNT(vote)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voteCount;
	}

	public String getVoteAvg(String vote,String coinId) {
		double voteAvg = 0;
		try {
			Connection conn = daoFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT AVG(vote_percentage) FROM vote WHERE vote=? AND Id_coin=? ");
			ps.setString(1, vote);
			ps.setString(2, coinId);
			ResultSet rs = ps.executeQuery();
			rs.first();
			voteAvg = rs.getDouble("AVG(vote_percentage)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return df.format(voteAvg);
	}
	
	@Override
	public void addVote(String coinId, String voteType, double votePercentage) {
		try {
			Connection conn = daoFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO vote VALUES (?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2,coinId);
			ps.setString(3,voteType);
			ps.setDouble(4, votePercentage);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
