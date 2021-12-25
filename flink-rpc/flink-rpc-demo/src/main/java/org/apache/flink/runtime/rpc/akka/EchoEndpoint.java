package org.apache.flink.runtime.rpc.akka;

import org.apache.flink.runtime.rpc.RpcEndpoint;
import org.apache.flink.runtime.rpc.RpcService;

public class EchoEndpoint extends RpcEndpoint implements EchoService {

    public EchoEndpoint(RpcService rpcService) {
        super(rpcService);
    }

    public EchoEndpoint(RpcService rpcService, String endpointId) {
        super(rpcService, endpointId);
    }

    @Override
    public String echo(String hello) {
        System.out.println("收到请求: " + hello);
        return String.format("hello, %s!", hello);
    }
}
