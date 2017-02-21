package com.zqh.hessian.client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.zqh.hessian.bo.Person;
import org.apache.commons.io.IOUtils;

import com.caucho.hessian.client.HessianProxyFactory;


/**
 * Created by OrangeKiller on 2017/2/21.
 */
public class HessianClient {
    //Hessian RPC远程服务url
    private static final String url = "http://localhost:8080/hessian/hessianRPC";

    public static void main(String[] args) throws Exception{
        //创建HessianProxyFactory实例
        HessianProxyFactory factory = new HessianProxyFactory();

        //开启方法重载
        factory.setOverloadEnabled(true);

        //获得Hessian服务的远程引用
        HessianServer hessianServer = (HessianServer)factory.create(HessianServer.class, url);

        //调用方法
        System.out.println("call say():" + hessianServer.say());
        System.out.println("call say(\"Tom\"):" + hessianServer.say("Tom"));
        System.out.println("call getPerson():");

        //调用方法获取集合对象
        List<Person> persons = hessianServer.getPerson();
        if (null != persons && persons.size() > 0) {
            for (Person p : persons) {
                System.out.println(p.toString());
            }
        } else {
            System.out.println("No person.");
        }

        //通过参数调用方法获取对象
        int id = 2;
        System.out.println(String.format("call getPersonById(%d)", id));
        Person person = hessianServer.getPersonById(id);
        if (null != person) {
            System.out.println(person.toString());
        } else {
            System.out.println("Id is " + id + " person not exist.");
        }

        uploadFile(hessianServer);
        downloadFile(hessianServer);
    }

    /**
     * 上传文件
     * @param hessianServer
     */
    public static void uploadFile(HessianServer hessianServer){
        String file = "E:\\V5User\\workspace\\hessian\\hessianserver\\src\\main\\webapp\\WEB-INF\\pages\\404.html";//需要上传的文件
        String file_path= "E:\\V5User\\workspace\\hessian\\hessianserver\\";//上传文件到服务器的路径
        InputStream data = null;
        try {
            data = new BufferedInputStream(new FileInputStream(file));
            if (hessianServer.upload("work_opt.txt", data)) {
                System.out.println("Upload file " + file_path + " succeed.");
            } else {
                System.out.println("Upload file " + file_path + " failed.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(data);
        }
    }

    /**
     * 下载文件
     * @param hessianServer
     */
    public static void downloadFile(HessianServer hessianServer){
        byte[] temp = null ;
        String file_name = "work_opt.txt" ;
        String download_path = "E:\\V5User\\workspace\\hessian\\hessianclient\\";//文件下载后存放的路径
        try {
            temp = hessianServer.download(file_name);
            if (null != temp) {
                FileWriter output = new FileWriter(download_path + file_name);
                IOUtils.write(temp, output, "UTF-8");
                System.out.println("Download file " + download_path + " succeed.");
                output.close();
            } else {
                System.out.println("Download file " + download_path + " failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}