package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import error.IdNotFound;
import model.Product;
import service.ServiceStock;

public class ServiceStockTest {

    
    @Test
    public void testDelete() throws Exception{
        
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        List<Product> list = new ArrayList<Product>();
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        ServiceStock spyStock = spy(stock);

        doReturn(p1).when(spyStock).findById(p1.getId());

        assertEquals(p1, spyStock.delete(p1.getId()));
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
    public void testFindById() throws Exception {
        
        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        assertEquals(p1,stock.findById(p1.getId()));
    }

    @Test
    public void testFindByIndex() throws Exception {
        
        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        assertEquals(0,stock.findByIndex(p1.getId()));
    }

    @Test
    public void testFindByIndexIdNotFound() throws Exception {
        
        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        assertThrows(IdNotFound.class, () ->{
            stock.findByIndex(UUID.randomUUID());
        });
        
    }

    @Test
    public void testFindByIndexIllegalArgumentException() throws Exception {

        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        list.add(p1);
        ServiceStock stock = new ServiceStock(list);

        assertThrows(IllegalArgumentException.class, () ->{
            stock.findByIndex(null);
        });
        
    }

    @Test
    public void testSave() {
        
        ArrayList<Product> list = new ArrayList<Product>();
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        ServiceStock stock = new ServiceStock(list);

        assertEquals(p1, stock.save(p1));
    }

    @Test
    public void testSaveIllegalArgumentException() {
        ServiceStock stock = new ServiceStock(new ArrayList<Product>());

        assertThrows(IllegalArgumentException.class, () ->{
            stock.save(null);
        });
    }

    @Test
    public void addToStock() throws IllegalArgumentException, IdNotFound {
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        ServiceStock stock = new ServiceStock(new ArrayList<Product>());

        ServiceStock spyStock = spy(stock);

        doReturn(p1).when(spyStock).findById(p1.getId());

        assertEquals(p1.getQuantity() + 10, spyStock.addToStock(p1.getId(), 10).getQuantity());
    }
    @Test
    public void addToStockNegativenumber() throws IllegalArgumentException, IdNotFound {
        Product p1 = new Product("ps5", "ps5",5000,1,1);
        ServiceStock stock = new ServiceStock(new ArrayList<Product>());

        ServiceStock spyStock = spy(stock);

        doReturn(p1).when(spyStock).findById(p1.getId());

        assertEquals(null, spyStock.addToStock(p1.getId(), -10));
    }

    @Test
    public void removeFromStockNegativeNunber() throws IdNotFound {

        Product p1 = new Product("ps5", "ps5",5000,2,1);
        ServiceStock stock = new ServiceStock(new ArrayList<Product>());

        ServiceStock spyStock = spy(stock);

        doReturn(p1).when(spyStock).findById(p1.getId());

        assertEquals(p1.getQuantity() -1, spyStock.removeFromStock(p1.getId(), -1).getQuantity());


    }
    @Test
    public void removeFromStockPositiveNunber() throws IdNotFound {

        Product p1 = new Product("ps5", "ps5",5000,2,1);
        ServiceStock stock = new ServiceStock(new ArrayList<Product>());

        ServiceStock spyStock = spy(stock);

        doReturn(p1).when(spyStock).findById(p1.getId());

        assertEquals(p1.getQuantity() -1, spyStock.removeFromStock(p1.getId(), 1).getQuantity());


    }
}
