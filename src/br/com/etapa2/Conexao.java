package br.com.etapa2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	 static final String DRIVER = "org.postgresql.Driver";
	 static final String USER = "postgres";
	 static final String SENHA = "7596";
	 static final String URL = "jdbc:postgresql://localhost:5432/projeto-etapa2";
	 
	 public static Connection criarConexao() throws ClassNotFoundException, SQLException {
		 
		 try {
			 Class.forName(DRIVER);
			 Connection con = DriverManager.getConnection(URL, USER, SENHA);
			 
			 if(con != null) {
				 return con;	 
			 }
			 
		 } catch (ClassNotFoundException ex){
			 System.err.print(ex.getMessage());
		 } catch (SQLException e){
			 System.err.print(e.getMessage());
		 }
		return null;
	 }
}
	 