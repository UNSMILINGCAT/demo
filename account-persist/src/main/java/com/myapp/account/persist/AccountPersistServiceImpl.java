package com.myapp.account.persist;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

public class AccountPersistServiceImpl implements AccountPersistService
{
    private String file;
    private SAXReader reader = new SAXReader();

    private Document readDocument() throws AccountPersistException
    {
        File dataFile = new File(file);
        if (!dataFile.exists())
        {
            dataFile.getParentFile().mkdir();
            Document doc = DocumentFactory.getInstance().createDocument();
            Element rootEle = doc.addElement(ELEMENT_ROOT);
            rootEle.addElement(ELEMENT_ACCOUNTS);
            writeDocument(doc);
        }
        try
        {
            return reader.read(new File(file));
        } catch (DocumentException de)
        {
            throw new AccountPersistException();
        }
    }

    public void writeDocument(Document doc) throws AccountPersistException
    {
        Writer out = null;
        try
        {
            out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
            writer.write(doc);
        } catch (IOException e)
        {

        } finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            } catch (IOException e)
            {

            }
        }
    }

    public Account createAccount(Account account) throws AccountPersistException
    {
        return null;
    }

    public Account readAccount(String id) throws AccountPersistException
    {
        Document document = readDocument();
        Element accountElement = document.getRootElement().element("");
        for (Element accountEle : (List<Element>) accountElement.elements())
        {
            if (accountEle.elementText("").equals(id))
            {
                return buildAccount(accountEle);
            }
        }
        return null;
    }

    private Account buildAccount(Element element)
    {
        Account account = new Account();
        account.setId(element.elementText(""));
        account.setName(element.elementText(""));
        account.setEmail(element.elementText(""));
        account.setPassword(element.elementText(""));
        account.setActivated(("true".equals(element.elementText("")) ? true : false));
        return account;
    }

    public Account updateAccount(Account account) throws AccountPersistException
    {
        return null;
    }

    public void deleteAccount(String id) throws AccountPersistException
    {

    }
}
