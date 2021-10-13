package daos;

import entities.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends DaoAbstract<Item>{
    @Override
    public int save(Item entity) {
        int id=0;
        final String QUERY = "INSERT INTO prescriptions_items ( prescriptions_id, name, quantity) VALUES(?, ?, ?)";
        try(PreparedStatement preparedStatement = idReturnStatementForVarArgs(QUERY , entity.getPrescriptionId() ,
                entity.getName() , entity.getQuantity() )){
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id= resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public List<Item> getByPrescriptionId(int id){
        final String QUERY = "SELECT id, prescriptions_id, name, quantity, ifexists, unit_price " +
                "FROM prescriptions_items WHERE prescriptions_id = ?";
        List<Item> itemList = new ArrayList<>();
        try(PreparedStatement preparedStatement = statementForVarArgs(QUERY , id);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                itemList.add(convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    public void deleteByPrescriptionId(int id){
        final String QUERY = "DELETE FROM prescriptions_items WHERE prescriptions_id = ?";
        try(PreparedStatement preparedStatement = statementForVarArgs(QUERY , id) ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void confirm(Item item){
        final String QUERY = "UPDATE prescriptions_items SET ifexists=? , unit_price = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = statementForVarArgs(QUERY ,item.getExists() , item.getUnitPrice(), item.getId()) ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item convert(ResultSet resultSet) throws SQLException {
        int itemId = resultSet.getInt(1);
        int prescriptionId = resultSet.getInt(2);
        String itemName = resultSet.getString(3);
        int itemQuantity = resultSet.getInt(4);
        Boolean exits = resultSet.getBoolean(5);
        Double itemPrice = resultSet.getDouble(6);
        return new Item( itemId, prescriptionId, itemName ,itemQuantity , exits ,itemPrice );
    }

}
