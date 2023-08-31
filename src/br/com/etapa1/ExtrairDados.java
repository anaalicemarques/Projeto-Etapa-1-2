package br.com.etapa1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtrairDados {
	
	public static void main(String[] args) {
		try {

			String url = "https://revistaoeste.com";
			String urlColunista = url + "/colunista";

			Document doc = (Document) Jsoup.connect(url).get();

			Elements elements = doc.select("a");

			String csvFileName = "dados.csv";
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName));

			writer.write("Lista de todos os links da pagina: " + url + "\n");

			for (Element element : elements) {
				String link = element.attr("href");

				if (!link.isEmpty()) {
						writer.write(link + "\n");
						writer.write("\n");
				}

			}

			writer.close();

			System.out.println("Dados extraídos e armazenados em " + csvFileName);
			
			String csvFileNameTratado = "dados-tratados.csv";
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(csvFileNameTratado));

			writer2.write("Lista de links dos colunistas da pagina: " + url + "\n");

			for (Element element : elements) {
				String link = element.attr("href");

				if (!link.isEmpty()) {
					if(link.contains(urlColunista)) {
						writer2.write(link + "\n");
						writer2.write("\n");
					}
				}

			}

			writer2.close();

			System.out.println("Dados extraídos e armazenados em " + csvFileNameTratado);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
