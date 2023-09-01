package br.com.etapa2;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CriarTabela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Statement st;

	public CriarTabela() throws ClassNotFoundException, SQLException {
		
		Connection con = Conexao.criarConexao();
		
		String sentencaSQL = "CREATE TABLE TB_LINKS_REVISTA_OESTE (codigo integer PRIMARY KEY, link VARCHAR(255));" ;

		try {
			st = con.createStatement();
			st.executeUpdate(sentencaSQL);
			JOptionPane.showMessageDialog(this,"Tabela criada com sucesso!","Mensagem", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this,"Não foi possível criar a tabela!\n" + "Mensagem: " + eSQL.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		
		try {
			st.close();
			con.close();
		} catch(Exception exception) {
			exception.printStackTrace();
			System.exit(3);
		}
		
		Container P = getContentPane();
		P.setLayout(new FlowLayout());
		JLabel mensagem = new JLabel("Você acabou de criar uma tabela");
		P.add(mensagem);
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		
		CriarTabela ex = new CriarTabela();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setTitle("USANDO CREATE TABLE");
		ex.setVisible(true);
		ex.setSize(400,300);
	}
	
}