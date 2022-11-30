package maven.nations.it;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {
	

	private static final String URL = "jdbc:mysql://localhost:3306/db-nations";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static void main(String[] args) {
		
		query1();
	}
		
		
	private static void query1() {
	
		
		try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
			
			final String sql = " SELECT countries.country_id, countries.name AS country, regions.name AS region, continents.name AS continent "
								+ " FROM countries "
								+ "	JOIN regions "
								+ "	ON countries.region_id = regions.region_id "
								+ "	JOIN continents "
								+ "	ON regions.continent_id = continents.continent_id "
								+ " ORDER BY countries.name ";
						
			try(PreparedStatement ps = con.prepareStatement(sql)){
				
				try(ResultSet rs = ps.executeQuery()){
					
					while(rs.next()) {
						
						final int id = rs.getInt(1);
						final String country = rs.getString(2);
						final String region = rs.getString(3);
						final String continent = rs.getString(4);
						
						System.out.println(id + " - " + country + " - " + region + " - " + continent);
					}
					
					System.out.println();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}

//try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
//	
//	final String sql = "SELECT * FROM roles";
//	
//	try(PreparedStatement ps = con.prepareStatement(sql)){
//		
//		try(ResultSet rs = ps.executeQuery()){
//			
//			while(rs.next()) {
//				
//				final int id = rs.getInt(1);
//				final String name = rs.getString(2);
//				
//				System.out.println(id + " - " + name);
//			}
//		}
//	}
//	
//} catch (Exception e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}