package interfaces;

import java.util.List;
import java.util.UUID;

public interface InterfaceCRUD<T, ID> {

    public T update(T object, UUID id);

    public T update(T object);

    public T save(T object);

    public T delete(ID id);

    public T findById(ID id);

    public Integer findByIndex(ID id);

    public List<T> findAll();

}
