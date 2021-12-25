package org.apache.flink.runtime.rpc.akka;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.rpc.RpcService;
import org.apache.flink.runtime.rpc.RpcSystem;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Consumer {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        AkkaRpcSystemLoader akkaRpcSystemLoader = new AkkaRpcSystemLoader();
        RpcSystem rpcSystem = akkaRpcSystemLoader.loadRpcSystem(config);
        RpcSystem.RpcServiceBuilder remoteServiceBuilder = rpcSystem.remoteServiceBuilder(config, "", "19081");
        RpcService rpcService = remoteServiceBuilder.createAndStart();
        CompletableFuture<EchoService> future = rpcService.connect("akka.tcp://flink@192.168.1.104:19902/user/rpc/echo", EchoService.class);
        EchoService proxy = future.get(10, TimeUnit.SECONDS);
        String echo = proxy.echo("flink-rpc");
        System.out.println(echo);
    }
}
