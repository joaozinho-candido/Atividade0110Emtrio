package br.com.starlog.model;

import java.util.ArrayList;
import java.util.List;

import br.com.starlog.exception.CapacidadeExcedidaException;

public class ModuloCarga {

    private String idModulo;
    private int capacidadeMaxima;
    private List<Carga> cargas;

    public ModuloCarga(String idModulo, int capacidadeMaxima) {
        this.idModulo = idModulo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargas = new ArrayList<>();
    }

    public void carregarCarga(Carga carga) throws CapacidadeExcedidaException {
        if (this.cargas.size() >= this.capacidadeMaxima) {
            throw new CapacidadeExcedidaException("Capacidade maxima de " + this.capacidadeMaxima
                    + " atingida no modulo " + this.idModulo);
        }
        this.cargas.add(carga);
    }

    public double calcularSeguroTotal() {
        return cargas.stream()
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }

    public long contarPorCategoria(String categoria) {
        return cargas.stream()
                .filter(c -> c.getCategoria().equals(categoria))
                .count();
    }

    public double calcularSeguroPesadas(double pesoCorte) {
        return cargas.stream()
                .filter(c -> c.getPesoKg() > pesoCorte)
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }

    public String getIdModulo() {
        return idModulo;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public List<Carga> getCargas() {
        return cargas;
    }
}
