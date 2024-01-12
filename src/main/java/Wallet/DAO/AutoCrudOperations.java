package Wallet.DAO;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoCrudOperations<T> implements CrudOperations<T> {

    private static String FIND_ALL_QUERY = "select {COLUMNS} from {TABLES}";
    private Connection connection;


    public AutoCrudOperations(Connection connection) {
        this.connection = connection;
    }


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

        List<T> resultList = new ArrayList<>();
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        List<String> attributeNames;
        try {
            attributeNames = getAttributes(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return resultList;
        }
        String tableName = getTableName(clazz);
        String query = FIND_ALL_QUERY
                .replace("{COLUMNS}", String.join(", ", attributeNames))
                .replace("{TABLES}", tableName);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                resultList.add(instance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return resultList;


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
