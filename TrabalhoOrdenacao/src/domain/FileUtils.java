package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FileUtils {

    public Ordenavel[] lerArquivo(String nomeArquivo)
            throws NumberFormatException, IOException {
        List<Ordenavel> lista = new ArrayList<Ordenavel>();
        FileReader reader = new FileReader(new File(nomeArquivo));
        BufferedReader leitor = new BufferedReader(reader);
        String linha = null;
        while ((linha = leitor.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(linha, ",");
            int codigo = Integer.parseInt(tokenizer.nextToken());
            String string = tokenizer.nextToken();
            lista.add(new Ordenavel(codigo, string));
        }
        leitor.close();
        reader.close();
        Ordenavel[] ret = lista.toArray(new Ordenavel[0]);
        return ret;

    }

    @SuppressWarnings("rawtypes")
    public void gravarArquivo(Ordenavel[] vetor) throws IOException {
        apagarArquivo();
        List<Ordenavel> lista = Arrays.asList(vetor);
        FileWriter writer = new FileWriter(new File("depoisOrdenacao.txt"),
                true);
        PrintWriter saida = new PrintWriter(writer, true);
        for (Comparable o : lista) {
            saida.println(o.toString());
        }

        saida.close();
        writer.close();
    }

    /**
     * Apaga o arquivo apos a ordenacao
     */
    public void apagarArquivo() {
        new File("depoisOrdenacao.txt").delete();
    }
}
