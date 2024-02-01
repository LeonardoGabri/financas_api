package com.financas.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BancoEnum {
    ITAU("Itaú", "ITAU"),
    SANTANDER("Santander", "SANTANDER"),
    C6("C6", "C6"),
    NUBANK("Nubank", "NUBANK"),
    BRADESCO("Bradesco", "BRADESCO"),
    BANCOBRASIL("Banco do Brasil", "BANCOBRASIL"),
    DIGIO("Digio", "DIGIO"),
    CONTA_CORRENTE("Conta Corrente", "CONTA_CORRENTE");

    private final String chave;
    private final String valor;

    BancoEnum(String chave, String valor){
        this.chave = chave;
        this.valor = valor;
    }

    public String getChave() {
        return this.chave;
    }

    public String getValor() {
        return this.valor;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static BancoEnum valueOfCodigo(final String codigo){
        for(final BancoEnum tipo: BancoEnum.values()) {
            if(tipo.getValor().equals(codigo) || tipo.name().equals(codigo) || tipo.getChave().equals(codigo)){
                return tipo;
            }
        }
        return null;
    }
}
