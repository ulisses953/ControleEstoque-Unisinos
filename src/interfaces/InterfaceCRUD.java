package interfaces;

import java.util.List;
import java.util.UUID;

public interface InterfaceCRUD<T, ID> {

    public T update(T object, UUID id) throws Exception ;

    public T update(T object) throws Exception;

    public T save(T object) throws Exception;

    public T delete(ID id) throws Exception ;

    public T findById(ID id) throws Exception;

    public Integer findByIndex(ID id)throws Exception;

    public List<T> findAll();

}
