package org.apache.flink.runtime.rpc.akka;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.rpc.RpcService;
import org.apache.flink.runtime.rpc.RpcSystem;

public class Provider {

    public static void main(String[] args) throws Exception {
        Configuration config = new Configuration();
        AkkaRpcSystemLoader akkaRpcSystemLoader = new AkkaRpcSystemLoader();
        RpcSystem rpcSystem = akkaRpcSystemLoader.loadRpcSystem(config);
        RpcSystem.RpcServiceBuilder remoteServiceBuilder = rpcSystem.remoteServiceBuilder(config, "", "19902");
        RpcService rpcService = remoteServiceBuilder.createAndStart();
        EchoEndpoint endpoint = new EchoEndpoint(rpcService, "echo");
        endpoint.start();
        System.out.println("echo address: " + endpoint.getAddress());
        System.in.read();
    }
}
