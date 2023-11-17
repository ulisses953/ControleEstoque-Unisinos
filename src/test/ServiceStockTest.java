package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import error.IdNotFound;
import model.Product;
import model.Stock;
import service.ServiceStock;

public class ServiceStockTest {

    
    @Test
    public void testDelete() throws Exception{
        ServiceStock stock = new ServiceStock(new ArrayList());


        ServiceStock spyStock = spy(stock);
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        stock.save(p1);

        doReturn(p1).when(spyStock).delete(p1.getId());

        assertEquals(p1, stock.delete(p1.getId()));
    }
    

    @Test
    public void testFindAll() {

        List<Product> list = new ArrayList<Product>();

        list.add(new Product("ps5", "ps5",5000,1,1));

        ServiceStock stock = new ServiceStock(list);

        assertEquals(1, stock.findAll().size());
    }

    @Test
    public void testFindAllNull() {

        ServiceStock stock = new ServiceStock(new ArrayList<>());

        assertEquals(0, stock.findAll().size());

    }

    @Test
    public void testFindByIdIllegalArgumentException() throws IllegalArgumentException, IdNotFound {
        
        ServiceStock stock = new ServiceStock(new ArrayList<>());

        assertThrows(IllegalArgumentException.class, () -> {
            stock.findById(null);
        });
        
    }

    @Test
    public void testFindByIdIdNotFound() throws IllegalArgumentException, IdNotFound {
        
        ServiceStock stock = new ServiceStock(new ArrayList<>());

        assertThrows(IdNotFound.class, () -> {
            stock.findById(UUID.randomUUID());
        });
    }

    @Test
    public void testFindByIdId() throws Exception {
        
        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        assertEquals(p1,stock.findById(p1.getId()) );
    }

    @Test
    public void testFindByIndex() {

    }

    @Test
    public void testGetEstoque() {

    }

    @Test
    public void testSave() {
        


    }

    @Test
    public void testSetEstoque() {

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testUpdate2() {

    }
}
