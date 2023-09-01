package br.com.etapa2;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InserirDados extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public InserirDados() throws SQLException, ClassNotFoundException, IOException {
	
		Connection con = Conexao.criarConexao();
		String sql = "INSERT INTO TB_LINKS_REVISTA_OESTE (codigo, link) VALUES (?, ?)";
		List<String> listaLinks = extrairDadosLinks();
		Integer codigo = 1;
		
		PreparedStatement preparador = con.prepareStatement(sql);
		
		for (String link : listaLinks) {
			
			try {
				preparador.setInt(1, codigo);
				preparador.setString(2, link);
				preparador.execute();
				codigo++;
				
				System.out.println();
				System.out.println("Inserção realizada!");
				
			} catch (SQLException e) {
				System.out.println("Erro - " + e.getMessage());
			}
		}
		
		preparador.close();

		Container P = getContentPane();
		P.setLayout(new FlowLayout());
		JLabel mensagem = new JLabel("Você acabou de inserir os dados na tabela!");
		P.add(mensagem);
	}
	
	public List<String> extrairDadosLinks() throws IOException {
		String url = "https://revistaoeste.com";
		String urlColunista = url + "/colunista";
		List<String> listaLinks = new ArrayList<>();

		Document doc = (Document) Jsoup.connect(url).get();

		Elements elements = doc.select("a");
		
		for (Element element : elements) {
			String link = element.attr("href");

			if (!link.isEmpty()) {
				if(link.contains(urlColunista)) {
					listaLinks.add(link);
				}
			}
		}
		
		return listaLinks;
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
		InserirDados ex = new InserirDados();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setVisible(true);
		ex.setTitle("USANDO INSERT");
		ex.setSize(400,200);
	}
	
}
