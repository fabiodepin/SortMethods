package domain;

import java.util.Arrays;

public class MetodosOrdenacao {

    private Ordenavel[] vet;
    private long tempo;
    private long movimentos = 0;
    private long comparacoes = 0;
    private long trocas = 0;

    public MetodosOrdenacao() {
    }
    public MetodosOrdenacao(Ordenavel[] vet) {
        this.vet = vet;
    }

    public Ordenavel[] getVet() {
        return vet;
    }

    public void setVet(Ordenavel[] vet) {
        this.vet = vet;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public long getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(long movimentos) {
        this.movimentos = movimentos;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public void setComparacoes(long comparacoes) {
        this.comparacoes = comparacoes;
    }

    public long getTrocas() {
        return trocas;
    }

    public void setTrocas(long trocas) {
        this.trocas = trocas;
    }

    @Override
    public String toString() {
        return "MetodosOrdenacao [vet=" + Arrays.toString(vet) + "]";
    }

    public Ordenavel[] bubbleSort() {
        clearAll();
        Ordenavel[] aux = this.vet;
        boolean houveTroca = true;
        long horaInicial = System.currentTimeMillis();
        while (houveTroca) {
            houveTroca = false;
            for (int i = 0; i < (aux.length) - 1; i++) {
                this.comparacoes++;
                if (aux[i].getCodigo() > aux[i + 1].getCodigo()) {
                    Ordenavel variavelAuxiliar = aux[i + 1];
                    this.movimentos++;
                    aux[i + 1] = aux[i];
                    this.movimentos++;
                    aux[i] = variavelAuxiliar;
                    this.movimentos++;
                    houveTroca = true;
                    this.trocas++;
                }
            }
        }
        this.tempo = System.currentTimeMillis() - horaInicial;
        return aux;
    }

    public Ordenavel[] insertionSort() {
        clearAll();
        Ordenavel[] aux = this.vet;
        int i, j;
        Ordenavel x;
        int length = aux.length;
        long horaInicial = System.currentTimeMillis();
        for (j = 1; j < length; j++) {
            x = aux[j];
            this.movimentos++;
            this.comparacoes++;
            for (i = j - 1; i >= 0 && aux[i].getCodigo() > x.getCodigo(); --i) {
                this.comparacoes++;
                aux[i + 1] = aux[i];
                this.movimentos++;
                this.trocas++;
            }
            aux[i + 1] = x;
            this.movimentos++;
        }
        this.tempo = System.currentTimeMillis() - horaInicial;
        return aux;
    }

    public Ordenavel[] shellSort() {
        clearAll();
        Ordenavel[] aux = this.vet;
        int n = this.vet.length;
        int h = n / 2;
        int j;
        Ordenavel c;
        long horaInicial = System.currentTimeMillis();
        while (h > 0) {
            for (int i = h; i < n; i++) {
                c = aux[i];
                this.movimentos++;
                j = i;
                this.comparacoes++;
                while (j >= h && aux[j - h].getCodigo() > c.getCodigo()) {
                    this.comparacoes++;
                    aux[j] = aux[j - h];
                    this.movimentos++;
                    j = j - h;
                    this.trocas++;
                }
                aux[j] = c;
                this.movimentos++;
            }
            h = h / 2;
        }
        this.tempo = System.currentTimeMillis() - horaInicial;
        return aux;
    }

    public Ordenavel[] quickSort() {
        clearAll();
        Ordenavel[] aux = this.vet;
        long horaInicial = System.currentTimeMillis();
        quickSortOrder(aux, 0, aux.length - 1);
        this.tempo = System.currentTimeMillis() - horaInicial;
        return aux;
    }

    public Ordenavel[] heapSort() {
        clearAll();
        Ordenavel[] aux = this.vet;
        long horaInicial = System.currentTimeMillis();
        buildMaxHeap(aux);
        int n = aux.length;

        for (int i = aux.length - 1; i > 0; i--) {
            swap(aux, i, 0);
            maxHeapify(aux, 0, --n);
        }
        this.tempo = System.currentTimeMillis() - horaInicial;
        return aux;
    }

    /* helpers */
    private void clearAll(){
        this.tempo = 0;
        this.comparacoes = 1;
        this.trocas = 0;
        this.movimentos = 0;
    }
    
    private void quickSortOrder(Ordenavel[] v, int ini, int fim) {
        int meio;
        if (ini < fim) {
            meio = partition(v, ini, fim);
            quickSortOrder(v, ini, meio);
            quickSortOrder(v, meio + 1, fim);
        }
    }

    private int partition(Ordenavel[] v, int ini, int fim) {
        int topo, i;
        Ordenavel pivo = v[ini];
        topo = ini;
        
        for (i = ini + 1; i <= fim; i++) {
            this.comparacoes++;
            if (v[i].getCodigo() < pivo.getCodigo()) {
                v[topo] = v[i];
                this.movimentos++;
                v[i] = v[topo + 1];
                this.movimentos++;
                topo++;
                this.trocas++;
            }
        }
        v[topo] = pivo;
        this.movimentos++;
        return topo;
    }

    private void buildMaxHeap(Ordenavel[] v) {
        for (int i = v.length / 2 - 1; i >= 0; i--) {
            maxHeapify(v, i, v.length);
        }
    }

    private void maxHeapify(Ordenavel[] v, int pos, int n) {
        int maxi;
        int l = 2 * pos + 1;
        int right = 2 * pos + 2;
        this.comparacoes++;
        if ((l < n) && (v[l].getCodigo() > v[pos].getCodigo())) {
            maxi = l;
        } else {
            maxi = pos;
        }
        this.comparacoes++;
        if (right < n && v[right].getCodigo() > v[maxi].getCodigo()) {
            maxi = right;
        }
        this.comparacoes++;
        if (maxi != pos) {
            swap(v, pos, maxi);
            maxHeapify(v, maxi, n);
        }
    }

    private void swap(Ordenavel[] v, int j, int aposJ) {
        Ordenavel aux = v[j];
        this.movimentos++;
        v[j] = v[aposJ];
        this.movimentos++;
        v[aposJ] = aux;
        this.movimentos++;
        this.trocas++;
    }
}
