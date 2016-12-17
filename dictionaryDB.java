import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class dictionaryDB {
	private String url = "jdbc:mysql://localhost:3306/online-dictionary?characterEncoding=utf8&useSSL=true";
	private String username = "root";
	private String password = "940623";
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	private String sql;
	
	public void Init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url , username , password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error! Cann't get connected to the database");
			e.printStackTrace();
		}
	}
	
	/*public String searchPassword(String username){
		String a = new String();
		return a;
	}*/
	
	public ArrayList<Integer> searchNumberOfLikes(String wordToBeLookedUp){		
		ArrayList<Integer> numOfLikes = new ArrayList<Integer>();
		try {
			sql = "Select * from likes where Word = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, wordToBeLookedUp); 
			rs = pst.executeQuery();
		 
			if(!rs.next()){
				sql = "Insert into likes(Word, Bing, Youdao, Jinshan) values(?, 0, 0, 0)" ;
				pst = conn.prepareStatement(sql);
				pst.setObject(1, wordToBeLookedUp);
				pst.execute();
				numOfLikes.add(0);
				numOfLikes.add(0);
				numOfLikes.add(0);
				return numOfLikes;
			}		
			
			numOfLikes.add(rs.getInt(2));
			numOfLikes.add(rs.getInt(3));
			numOfLikes.add(rs.getInt(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numOfLikes;
	}
	
	public void changeNumberOfLikes(String wordToBeLookedUp, int i, int mode){		
		try {
			//increse 1
			if(mode == 0){
				//Baidu
				if(i == 1){
					sql = "UPDATE likes SET Bing = Bing + 1 WHERE Word = ?";
				}
				//Youdao
				else if(i == 2){
					sql = "UPDATE likes SET Youdao = Youdao + 1 WHERE Word = ?";
				}
				//Jinshan
				else{
					sql = "UPDATE likes SET Jinshan = Jinshan + 1 WHERE Word = ?";
				}
			}
			//decrease 1
			else{
				//Baidu
				if(i == 1){
					sql = "UPDATE likes SET Bing = Bing - 1 WHERE Word = ?";
				}
				//Youdao
				else if(i == 2){
					sql = "UPDATE likes SET Youdao = Youdao - 1 WHERE Word = ?";
				}
				//Jinshan
				else{
					sql = "UPDATE likes SET Jinshan = Jinshan - 1 WHERE Word = ?";
				}
			}
			pst = conn.prepareStatement(sql);
			pst.setObject(1, wordToBeLookedUp); 
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	public String searchPassword(String account){
		String result = new String();
		try{
			sql = "Select password from user where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, account); 
			rs = pst.executeQuery();
			if(!rs.next()){
				result = "*";
			}
			else{
				result = rs.getString(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		return result;
	}
	
	public int searchID(){
		int result = 0;
		try {
			sql = "Select max(ID) from user";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			rs.next();
			String result_str = rs.getString(1);
			result = Integer.parseInt("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String addUser(int ID, String account, String password){
		String result = new String();
		try {
			sql = "SELECT * FROM user where account = ?";
			pst = conn.prepareStatement(sql);
			pst.setObject(1, account); 
			rs = pst.executeQuery();
		 
			if(!rs.next()){
				sql = "Insert into user(ID, name, password, account) values(?, ?, ?, ?)" ;
				pst = conn.prepareStatement(sql);
				pst.setObject(1, ID);
				pst.setObject(2, account);
				pst.setObject(3, password);
				pst.setObject(4, account);
				pst.execute();
				result = "Y";
			}
			else
				result = "N";				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}
	
	
	/*public static void main(String args[]){
		dictionaryDB test = new dictionaryDB();
		test.Init();
		ArrayList<Integer> result = test.searchNumberOfLikes("a");
		System.out.println(result.toArray()[0] + "\t" + result.toArray()[1]+ "\t" + result.toArray()[2]);
		
	}*/
}
