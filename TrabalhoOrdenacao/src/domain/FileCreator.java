package domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileCreator {

    private List<Ordenavel> lista;

    public FileCreator() {

        lista = criarListaAleatoria();

    }

    public void gravarArquivoAleatorio(List<Ordenavel> lista)
            throws IOException {
        FileWriter writer = new FileWriter(new File("saidaAleatoria.txt"), true);
        PrintWriter saida = new PrintWriter(writer, true);

        while (lista.size() > 0) {
            int indice = (int) (1 + Math.random() * (lista.size()) - 1);
            Ordenavel o = (Ordenavel) lista.get(indice);
            saida.println(o.toString());
            lista.remove(indice);
        }
        saida.close();
        writer.close();
    }

    public void gravarArquivo(List<Ordenavel> lista) throws IOException {
        FileWriter writer = new FileWriter(new File("saidaOrdenada.txt"), true);
        PrintWriter saida = new PrintWriter(writer, true);
        for (Ordenavel o : lista) {
            saida.println(o.toString());
        }

        saida.close();
        writer.close();
    }

    private List<Ordenavel> criarListaAleatoria() {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        List<Ordenavel> lista = new ArrayList<Ordenavel>();
        int indiceLetra = 0;

        for (int i = 0; i < 10000; i++) {
            // definicao do tamanho
            int tamanho = 0;
            while (tamanho == 0) {
                tamanho = (int) (1 + Math.random() * (letras.length()));
            }
            // loop de criacao da palavra
            String palavra = "";

            for (int j = 0; j < tamanho; j++) {
                indiceLetra = (int) (1 + Math.random() * (letras.length()) - 1);
                char letra = letras.charAt(indiceLetra);
                palavra += letra;
            }
            lista.add(new Ordenavel(i, palavra));

        }

        return lista;
    }

    public List<Ordenavel> getLista() {
        return lista;
    }

    public List<Ordenavel> ordenarLista() {
        Collections.sort(lista);
        return lista;

    }
}
