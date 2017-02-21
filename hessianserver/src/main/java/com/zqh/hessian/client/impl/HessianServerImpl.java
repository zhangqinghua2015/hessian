package com.zqh.hessian.client.impl;

import com.zqh.hessian.bo.Person;
import com.zqh.hessian.client.HessianServer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;


/**
 * Created by OrangeKiller on 2017/2/21.
 */

public class HessianServerImpl implements HessianServer {
    //上传或下载文件的地址
    private static String file_Path = "E:\\V5User\\workspace\\hessian\\hessianserver\\";

    private static Person[] persons = new Person[5];
    static {
        Random random = new Random();
        for(int i=0;i<persons.length;i++){
            persons[i] = new Person();
            persons[i].setId(i);
            persons[i].setGender(random.nextBoolean());
            persons[i].setName("name-" + i);
            persons[i].setPhone(random.nextLong());
            persons[i].setHeight(random.nextDouble());
            persons[i].setWeight(random.nextFloat());
            persons[i].setAddress(new String[]{"Address" + random.nextInt(),"Address" + random.nextInt()});

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, i+1);
            persons[i].setBirthday(calendar.getTime());
        }
    }

    @Override
    public String say() {
        return "Hello HessianRPC " + new Date().toString();
    }

    @Override
    public String say(String str) {
        return "Welcome" + str;
    }

    @Override
    public List<Person> getPerson() {
        return Arrays.asList(persons);
    }

    @Override
    public Person getPersonById(int id) {
        for(Person person : persons){
            if(person.getId() == id){
                return person ;
            }
        }
        return null;
    }
    @Override
    public boolean upload(String filename, InputStream data) {
        List<String> temp;
        try {
            temp = IOUtils.readLines(data);
            String filePath = file_Path + filename;
            FileUtils.writeLines(new File(filePath), temp);
            System.out.println("Upload file to " + filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public byte[] download(String filename) {
        String filePath = file_Path + filename;
        InputStream data = null;
        try {
            data = new FileInputStream(filePath);
            int size = data.available();
            byte[] buffer = new byte[size];
            IOUtils.read(data, buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(data);
        }
    }
}