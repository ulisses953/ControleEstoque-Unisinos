package interfaces;

import java.util.List;
import java.util.UUID;

public interface InterfaceCRUD<T, E> {

    public T update(T objeto, UUID id);

    public T update(T objeto);

    public T salvar(T obejeto);

    public T excluir(UUID id);

    public T findbyid(UUID id);

    public List<T> findAll();

}
