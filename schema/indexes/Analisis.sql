BILLS{
    QUERY:     List<Bill> findBillsBtwDates(String startDate, String finalDate);
        select  b.* from bills b
        where b.datee BETWEEN concat('2000-10-10', ' 00:00:00') and DATE_ADD(concat( '2020-10-10', ' 00:00:00'), interval 1 DAY)
        group by b.datee desc
        order by b.datee desc;

    INDEX:(Sin index type:ALL, con index type:INDEX)
        CREATE INDEX IDX_bills_datee ON
        bills (datee desc) USING BTREE;

}
CALLS{
    QUERY:    List<Call> getCallsBtwDates(String date1, String date2);
        select  c.* from calls c
        where c.datee BETWEEN concat('2000-10-10', ' 00:00:00') and DATE_ADD(concat( '2020-10-10', ' 00:00:00'), interval 1 DAY)
        group by c.datee desc
        order by c.datee desc;

    INDEX:(Sin index type:ALL, con index type:INDEX)
        CREATE INDEX IDX_calls_datee ON
        calls (datee desc) USING BTREE;

    QUERY:    List<Call> getCallsFromByUser(Integer idUser);
        select c.*
        from calls c
        inner join phone_lines pl on c.phone_number_from = pl.phone_number
        where pl.id_user = 1
        order by c.id_bill asc;

    INDEX:(Sin index type:ALL, con index type:REF;
        create INDEX IDX_calls_phone_line_from ON
        calls (phone_number_from asc) USING BTREE;

     Optimiza más de una Query:     List<Call> getCallsFromByUser(Integer idUser);
     
}

