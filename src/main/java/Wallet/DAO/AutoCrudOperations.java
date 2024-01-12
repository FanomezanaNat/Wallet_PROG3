package Wallet.DAO;

import java.lang.reflect.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AutoCrudOperations<T>  implements CrudOperations<T> {

    private Connection connection;
    private static final String FIND_ALL_QUERY = "select {COLUMNS} from {TABLES}";

    private List<String> getAttributes(Class<T> clazz) throws IllegalAccessException {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    private String getTableName(Class<T> clazz) {
        return clazz.getSimpleName();
    }

    @Override
    public List<T> findAll() {

        List<Object> attributes;
        String tableName;

        return null;

    }

    @Override
    public List<T> saveAll(List<T> toSave) {
        return null;
    }

    @Override
    public T save(T toSave) {
        return null;
    }

    @Override
    public T delete(T toDelete) {
        return null;
    }
}
