package org.domiexpress.persistencia;

import org.domiexpress.pedido.Pedido;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepositorioPedidos {
    void guardar(Pedido pedido);
    Optional<Pedido> buscarPorId(String id);
    List<Pedido> listarTodos();

}
