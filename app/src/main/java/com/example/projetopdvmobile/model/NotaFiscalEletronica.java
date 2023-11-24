package com.example.projetopdvmobile.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotaFiscalEletronica {
    private Pedido pedido;
    private int cod_nota_fiscal;
    private Date data_emissao;
    private Adm adm ;


    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCod_nota_fiscal() {
        return cod_nota_fiscal;
    }

    public void setCod_nota_fiscal(int cod_nota_fiscal) {
        this.cod_nota_fiscal = cod_nota_fiscal;
    }

    public Date getData_emissao() {
        return data_emissao;
    }

    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }

    public Adm getAdm() {
        return adm;
    }

    public void setAdm(Adm adm) {
        this.adm = adm;
    }

    public NotaFiscalEletronica(Pedido pedido, int cod_nota_fiscal, Date data_emissao, Adm adm) {
        this.pedido = pedido;
        this.cod_nota_fiscal = cod_nota_fiscal;
        this.data_emissao = data_emissao;
        this.adm = adm;
    }

    public NotaFiscalEletronica() {
    }
}