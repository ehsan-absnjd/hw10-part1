package daos;


import entities.Item;
import entities.Prescription;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrescriptionDao extends DaoAbstract<Prescription> {
    ItemDao itemDao = new ItemDao();

    @Override
    public int save(Prescription entity) {
        int id = 0;
        final String QUERY = "INSERT INTO prescriptions (patient_id, isconfirmed) VALUES( ?, false)";
        try(PreparedStatement preparedStatement = idReturnStatementForVarArgs(QUERY , entity.getPatientId()) ){
            id = preparedStatement.executeUpdate();
            for (Item item : entity.getItemList()){
                item.setPrescriptionId(id);
                itemDao.save(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public List<Prescription> getAll(){
        final String QUERY = "SELECT id, isconfirmed, patient_id FROM prescriptions";
        List<Prescription> prescriptionList =new ArrayList<>();
        try(PreparedStatement preparedStatement= statementForVarArgs(QUERY);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                prescriptionList.add(convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptionList;
    }
    public Optional<Prescription> getById(int id){
        final String QUERY = "SELECT id, isconfirmed, patient_id FROM prescriptions WHERE id = ?";
        Optional<Prescription> prescription =null;
        try(PreparedStatement preparedStatement= statementForVarArgs(QUERY, id);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                prescription = Optional.of(convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescription;
    }
    public void update(int prescriptionId , List<Item> items ){
        Optional<Prescription> prescriptionOptional = getById(prescriptionId);
        if(prescriptionOptional.isPresent()){
            Prescription prescription = prescriptionOptional.get();
            itemDao.deleteByPrescriptionId(prescriptionId);
            for(Item item : items){
                item.setPrescriptionId(prescriptionId);
                itemDao.save(item);
            }
        }
    }

    private Prescription convert(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        boolean isConfirmed = resultSet.getBoolean(2);
        int patientId = resultSet.getInt(3);
        List<Item> items = itemDao.getByPrescriptionId(id);
        return new Prescription( id, patientId, isConfirmed, items);
    }
}
