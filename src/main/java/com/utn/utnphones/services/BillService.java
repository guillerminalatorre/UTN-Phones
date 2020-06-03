package com.utn.utnphones.services;
import java.util.ArrayList;
import java.util.List;
import com.utn.utnphones.models.Bill;
import com.utn.utnphones.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService (final BillRepository billRepository){
        this.billRepository = billRepository;
    }

    public List<Bill> getBills(){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findAll();

        return bills;
    }

    public Bill getBillById(Integer idBill){
        Bill bill = new Bill();

        bill = this.billRepository.findById(idBill).get();

        return bill;
    }

    public List<Bill> getBillsByPhoneNumber(String phone_number){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findByPhoneNumber(phone_number);

        return bills;
    }

    public List<Bill> getBillsPaid(){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findAllPaids();

        return bills;
    }

    public List<Bill> getBillsUnpaid(){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findAllUnpaid();

        return bills;
    }

    public List<Bill> getBillsFromDate(String date){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findBillsFromDate(date);

        return bills;
    }

    public List<Bill> getBillsBtwDates(String startDate, String finalDate){
        List<Bill> bills = new ArrayList<Bill>();

        bills = this.billRepository.findBillsBtwDates(startDate, finalDate);

        return bills;
    }

    public String getNumberById(Integer idBill){
        String number = new String();

        number = this.billRepository.findPhoneNumberById( idBill);

        return number;
    }

    public void addBill(Bill bill) {
        this.billRepository.save(bill);
    }
}
