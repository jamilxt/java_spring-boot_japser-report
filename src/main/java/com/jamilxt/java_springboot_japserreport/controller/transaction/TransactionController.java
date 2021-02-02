package com.jamilxt.java_springboot_japserreport.controller.transaction;

import com.jamilxt.java_springboot_japserreport.model.transaction.Transaction;
import com.jamilxt.java_springboot_japserreport.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements TransactionApi {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public ResponseEntity<List<Transaction>> getTransactionList() {
    return ResponseEntity.ok(transactionService.getTransactionList());
  }
}
