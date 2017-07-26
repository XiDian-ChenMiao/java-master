package cn.xidian.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyBlobTestWrite {
	public static void insert(){
		Connection conn = ConnectionFactory.getConnectionByMysql();
		PreparedStatement stmt = null;
		String sql = "insert into otherinfo(Name,Content,PhotoImage) values (?,?,?)";
		BufferedReader reader = null;
		InputStream img = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "ChenMiao");
			//Read the input stream from content.txt
			InputStream txt = Thread.currentThread().getContextClassLoader().getResourceAsStream("DatabaseUti/resource/content.txt");
			reader = new BufferedReader(new InputStreamReader(txt));
			stmt.setCharacterStream(2, reader);
			//Read the input stream from image file.
			img = Thread.currentThread().getContextClassLoader().getResourceAsStream("DatabaseUti/resource/Me.jpg");
			stmt.setBinaryStream(3, img);
			if(stmt.executeUpdate() == 1)
				System.out.println("Successfully add a record.");
			else
				System.out.println("Failly add a record");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(reader != null)
					reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(img != null)
					img.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			DbClose.close(stmt,conn);
		}
	}
}
