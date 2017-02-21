package com.zqh.hessian.client;

import com.zqh.hessian.bo.Person;

import java.io.InputStream;
import java.util.List;

/**
 * Created by OrangeKiller on 2017/2/21.
 */
public interface HessianServer {
    public String say();
    public String say(String str);
    public List<Person> getPerson();
    public Person getPersonById(int id);
    public boolean upload(String filename,InputStream data);
    public byte[] download(String filename);
}
