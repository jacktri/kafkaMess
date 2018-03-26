package com.embedded.zookeeper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import kafka.utils.ZKStringSerializer$;

public class EmbeddedZookeeperApplication {

    public static void main(String[] args) throws IOException{
        FileUtils.deleteDirectory(new File("/tmp"));
        initKafka();
    }

    private static void initKafka() {
        Properties zkProperties = new Properties();

        try {
            InputStream zooKeeperProps = new ClassPathResource("zklocal.properties").getInputStream();
            zkProperties.load(zooKeeperProps);

            ZooKeeperLocal zookeeper = new ZooKeeperLocal(zkProperties);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
