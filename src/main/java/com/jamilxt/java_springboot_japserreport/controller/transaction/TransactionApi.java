package com.jamilxt.java_springboot_japserreport.controller.transaction;

import com.jamilxt.java_springboot_japserreport.model.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "transactions")
public interface TransactionApi {

  @GetMapping
  ResponseEntity<List<Transaction>> getTransactionList();
}
