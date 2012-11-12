package domain;

public class Ordenavel implements Comparable<Object> {

    private int codigo;
    private String string;

    public Ordenavel(int codigo, String string) {
        this.codigo = codigo;
        this.string = string;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getString() {
        return string;
    }

    @Override
    public int compareTo(Object arg0) {
        Ordenavel ord = (Ordenavel) arg0;
        return (this.getCodigo() - ord.getCodigo());
    }

    @Override
    public String toString() {
        return (codigo + "," + string);
    }
}
