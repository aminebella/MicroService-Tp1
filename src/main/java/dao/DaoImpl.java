package dao;

import java.net.StandardSocketOptions;
import java.sql.SQLOutput;

public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        System.out.println("[DAO]: Recupreration de la valeur.");
        return 100.00;
    }
}
