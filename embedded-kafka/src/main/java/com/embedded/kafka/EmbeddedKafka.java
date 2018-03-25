package com.embedded.kafka;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import kafka.admin.AdminUtils;
import kafka.log.LogConfig;
import kafka.utils.ZKStringSerializer$;

public class EmbeddedKafka {

    private static KafkaLocal kafka;

    public static void main(String[] args) {
        initKafka();
    }

    private static void initKafka() {
        Properties kafkaProperties = new Properties();
        try {
            //load properties
            InputStream kafkaProps = new ClassPathResource("kafkalocal.properties").getInputStream();

            kafkaProperties.load(kafkaProps);

            //start kafka
            kafka = new KafkaLocal(kafkaProperties);
//            Thread.sleep(5000);
            String zookeeperConnect = "localhost:2181";
            int sessionTimeoutMs = 10 * 1000;
            int connectionTimeoutMs = 8 * 1000;

            ZkClient zkClient = new ZkClient(
                zookeeperConnect,
                sessionTimeoutMs,
                connectionTimeoutMs,
                ZKStringSerializer$.MODULE$);

            String topic = "ero";
            int partitions = 1;
            int replication = 1;

            // Add topic configuration her
            Properties props = new Properties();
            Map<String, String> configs = new HashMap<>();


//            props.setProperty(LogConfig.RetentionMsProp(), Long.toString(retentionMs));
//            props.setProperty(LogConfig.CleanupPolicyProp(), DEFAULT_CLEANUP_POLICY);

            AdminUtils.createTopic(zkClient, topic, partitions, replication, props);
            zkClient.close();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}