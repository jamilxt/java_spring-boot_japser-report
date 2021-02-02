package com.jamilxt.java_springboot_japserreport.service.transaction;

import com.jamilxt.java_springboot_japserreport.model.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

  public List<Transaction> getTransactionList() {

    List<Transaction> transactionList = new ArrayList<>();

    Transaction transactionOne = new Transaction("2020/11/16", "11:18:26", "SITE TEST",
        "UNI001TEST - Basic2", "00003427 - PCP 3427", "UNI004AS - Salandy Aaron", "979548",
        "IDContribuyente01", "E1A1XONM - 000460", "UNI004AS - Salandy Aaron",
        "Card - 7095030162260000027", "Unleaded Plus ", 0.747, 5.79, 7.75);

    Transaction transactionTwo = new Transaction("2020/11/16", "11:18:26", "SITE TEST",
        "UNI001TEST - Basic2", "00003427 - PCP 3427", "UNI004AS - Salandy Aaron", "979548",
        "IDContribuyente01", "E1A1XONM - 000460", "UNI004AS - Salandy Aaron",
        "Card - 7095030162260000027", "Unleaded Plus ", 0.747, 5.79, 7.75);

    transactionList.add(transactionOne);
    transactionList.add(transactionTwo);

    return transactionList;
  }
}
