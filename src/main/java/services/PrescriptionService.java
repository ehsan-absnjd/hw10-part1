package services;

import daos.PrescriptionDao;
import entities.Item;
import entities.Prescription;

import java.util.List;
import java.util.Optional;

public class PrescriptionService {
    private PrescriptionDao prescriptionDao = new PrescriptionDao();
    public int save(Prescription prescription) {
        return prescriptionDao.save(prescription);
    }

    public List<Prescription> getConfirmedByPatientId(int id) {
        return prescriptionDao.getConfirmedByPatientId(id);
    }

    public Optional<Prescription> getById(int id) {
        return prescriptionDao.getById(id);
    }

    public void update(int id, List<Item> itemList) {
        prescriptionDao.update(id , itemList);
    }

    public void delete(int id) {
        prescriptionDao.delete(id);
    }

    public List<Prescription> getAll() {
        return prescriptionDao.getAll();
    }

    public void confirm(Prescription prescription) {
        prescriptionDao.confirm(prescription);
    }
}
