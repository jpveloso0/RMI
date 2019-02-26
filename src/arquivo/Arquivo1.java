package arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Arquivo1 {

	public static void main(String[] args) {
		
		String pathHome = System.getProperty("user.home");
		
		System.out.println(pathHome);
		
		String pathCurrentDir = System.getProperty("user.dir");
		
		System.out.println(pathCurrentDir);
		
		Path pathDirs = Paths.get(pathCurrentDir, "teste", "arquivos");
		
		Path pathArquivo = pathDirs.resolve("bancos.txt");
		
		try {
			Files.createDirectories(pathDirs);
			
			Files.createFile(pathArquivo);
			
			BufferedWriter bw = Files.newBufferedWriter(pathArquivo);
			bw.write("Banco do Brasil");
			bw.newLine();
			bw.write("Santander");
			bw.newLine();
			bw.write("Caixa Economica");
			bw.newLine();
			
			bw.close();
			
			BufferedReader br = Files.newBufferedReader(pathArquivo);
			List<Object> bancos = br.lines().collect(Collectors.toList());
			
			for(Object obj : bancos){
				System.out.println((String)obj);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
