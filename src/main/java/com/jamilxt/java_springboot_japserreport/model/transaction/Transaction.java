package com.jamilxt.java_springboot_japserreport.model.transaction;

public class Transaction {
  private String date;
  private String time;
  private String site;
  private String fleet;
  private String vehicle;
  private String driver;
  private String authCode;
  private String taxPayerId;
  private String trnNumber;
  private String subAccount;
  private String identification;
  private String fuel;
  private double volume;
  private double amount;
  private double unitPrice;

  public Transaction() {
  }

  public Transaction(String date, String time, String site, String fleet, String vehicle, String driver, String authCode, String taxPayerId, String trnNumber, String subAccount, String identification, String fuel, double volume, double amount, double unitPrice) {
    this.date = date;
    this.time = time;
    this.site = site;
    this.fleet = fleet;
    this.vehicle = vehicle;
    this.driver = driver;
    this.authCode = authCode;
    this.taxPayerId = taxPayerId;
    this.trnNumber = trnNumber;
    this.subAccount = subAccount;
    this.identification = identification;
    this.fuel = fuel;
    this.volume = volume;
    this.amount = amount;
    this.unitPrice = unitPrice;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getFleet() {
    return fleet;
  }

  public void setFleet(String fleet) {
    this.fleet = fleet;
  }

  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getAuthCode() {
    return authCode;
  }

  public void setAuthCode(String authCode) {
    this.authCode = authCode;
  }

  public String getTaxPayerId() {
    return taxPayerId;
  }

  public void setTaxPayerId(String taxPayerId) {
    this.taxPayerId = taxPayerId;
  }

  public String getTrnNumber() {
    return trnNumber;
  }

  public void setTrnNumber(String trnNumber) {
    this.trnNumber = trnNumber;
  }

  public String getSubAccount() {
    return subAccount;
  }

  public void setSubAccount(String subAccount) {
    this.subAccount = subAccount;
  }

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }

  public String getFuel() {
    return fuel;
  }

  public void setFuel(String fuel) {
    this.fuel = fuel;
  }

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }
}
