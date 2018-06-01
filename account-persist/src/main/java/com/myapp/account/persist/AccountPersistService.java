package com.myapp.account.persist;

public interface AccountPersistService
{
    public final String ELEMENT_ROOT="account-persist";
    public final String ELEMENT_ACCOUNTS="accounts";
    public Account createAccount(Account account) throws AccountPersistException;
    public Account readAccount(String id) throws AccountPersistException;
    public Account updateAccount(Account account) throws AccountPersistException;
    public void deleteAccount(String id) throws AccountPersistException;
}
