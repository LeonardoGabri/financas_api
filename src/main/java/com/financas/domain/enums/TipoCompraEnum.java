package com.financas.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoCompraEnum {
    PROVISIONAMENTO("Provisionamento", "PROVISIONAMENTO"),
    EFETIVO("Efetivo", "EFETIVO");
    private final String chave;
    private final String valor;

    TipoCompraEnum(String chave, String valor){
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
    public static TipoCompraEnum valueOfCodigo(final String codigo){
        for(final TipoCompraEnum tipo: TipoCompraEnum.values()) {
            if(tipo.getValor().equals(codigo) || tipo.name().equals(codigo) || tipo.getChave().equals(codigo)){
                return tipo;
            }
        }
        return null;
    }
}
