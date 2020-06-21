package com.design.patterns.strategy;

public class Main {
    public static void main(String[] args) {
        RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();
        Conta conta = new Conta();
        conta.deposita(1000.0);
        realizadorDeInvestimentos.realiza(conta, new Conservador());
    }
}