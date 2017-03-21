package spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository  // 表示注释类是DAO
public class JdbcSpittleRepository implements SpittleRepository{

	private JdbcOperations jdbc;
	
	@Autowired
	public JdbcSpittleRepository(JdbcOperations jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<Spittle> findSpittles(long max, int count) {
		return jdbc.query(
				"select id, message, created_at, latitude, longitude" +
				" from Spittle" +
				" order by created_at desc limit 20", 
				new SpittleRowMapper());
	}

	public Spittle findOne(Long id) {
		return new Spittle("你好", new Date());
	}
	
	private static class SpittleRowMapper implements RowMapper<Spittle> {
		public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spittle(
					rs.getLong("id"), 
					rs.getString("message"), 
					rs.getDate("created_at"), 
					rs.getDouble("longitude"),
					rs.getDouble("latitude"));
		}
	}
}
