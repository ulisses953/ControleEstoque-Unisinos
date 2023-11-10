package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Produto;
import service.ServiceEstoque;

public class ServiceEstoqueTest {

    @Test
    void testExcluir() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindbyid() {

    }

    @Test
    void testGetEstoque() {

    }

    @Test
    void testSalvar() {
        ServiceEstoque serviceEstoque = new ServiceEstoque(new ArrayList<Produto>());
        var p1 = new Produto("ps5","ps5 500gb",5000.00,3,2);

        assertEquals(p1, serviceEstoque.salvar(p1));
    }

    @Test
    void testSetEstoque() {

    }

    @Test
    void testUpdate() {

    }

    @Test
    void testUpdate2() {

    }
}
