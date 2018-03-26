package com.embedded.kafka;

import java.io.InputStream;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.log.LogConfig;
import kafka.security.auth.Acl;
import kafka.security.auth.SimpleAclAuthorizer;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import scala.collection.immutable.HashSet;
import scala.collection.immutable.Set;

public class EmbeddedKafka {

    private static KafkaLocal kafka;

    public static void main(String[] args) {
        initKafka();
    }

    private static void initKafka() {
        Properties kafkaProperties = new Properties();
        try {
            InputStream kafkaProps = new ClassPathResource("kafkalocal.properties").getInputStream();
            kafkaProperties.load(kafkaProps);
            kafka = new KafkaLocal(kafkaProperties);
            createTopic();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private static void createTopic() {

        String zookeeperConnect = "localhost:2181";
        int sessionTimeoutMs = 10 * 1000;
        int connectionTimeoutMs = 8 * 1000;

        ZkClient zkClient = new ZkClient(
            zookeeperConnect,
            sessionTimeoutMs,
            connectionTimeoutMs,
            ZKStringSerializer$.MODULE$);

        ZkUtils zkUtils = ZkUtils.apply(zkClient, true);
        String topic = "ero";
        int partitions = 1;
        int replication = 1;

        // Add topic configuration her
        Properties props = new Properties();
//        SimpleAclAuthorizer simpleAclAuthorizer = new SimpleAclAuthorizer();
//        Acl acl = Acl.AllowAllAcl();
//        Set<Acl> aclSet =  HashSet(acl);
//        aclSet.
//        simpleAclAuthorizer.addAcls(aclSet,null);
        AdminUtils.createTopic(zkUtils, topic, partitions, replication, props, new RackAwareMode.Disabled$());
        zkUtils.close();
        zkClient.close();
    }
}
