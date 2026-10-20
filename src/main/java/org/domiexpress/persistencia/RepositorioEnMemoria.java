package org.domiexpress.persistencia;

import org.domiexpress.pedido.Pedido;

import java.util.*;

public class RepositorioEnMemoria implements RepositorioPedidos {
    private final Map<String, Pedido> pedidos = new HashMap<>();

    @Override
    public void guardar(Pedido pedido){
        pedidos.put(pedido.getId(), pedido);
    }
    @Override
    public Optional<Pedido> buscarPorId(String id){
        return Optional.ofNullable(pedidos.get(id));
    }
    @Override
    public List<Pedido> listarTodos(){
        return new ArrayList<>(pedidos.values());
    }


}
