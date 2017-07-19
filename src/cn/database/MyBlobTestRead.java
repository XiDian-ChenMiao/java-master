package cn.database;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyBlobTestRead {
	public static void selectBlob(){
		Connection conn = ConnectionFactory.getConnectionByMysql();
		PreparedStatement stmt = null;
		String sql = "select * from otherinfo where name = ?";
		ResultSet rs = null;
		BufferedReader reader = null;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "ChenMiao");
			rs = stmt.executeQuery();
			while(rs.next()){
				reader = new BufferedReader(rs.getCharacterStream(3));
				String str = null;
				while((str = reader.readLine()) != null)
					System.out.println(str);
				Blob blob = rs.getBlob(4);
				BufferedInputStream input = new BufferedInputStream(blob.getBinaryStream());
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\学习测试\\Me.jpg"));
				byte[] buffer = new byte[1024];
				int count = -1;
				while((count = input.read(buffer, 0, 1024)) != -1){
					output.write(buffer, 0, count);
				}
				output.flush();
				output.close();
				System.out.println("写入成功");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbClose.close(stmt, conn);
		}
	}
}
