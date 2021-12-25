package org.apache.flink.runtime.rpc.akka;

import org.apache.flink.runtime.rpc.RpcGateway;

public interface EchoService extends RpcGateway {

    String echo(String hello);
}
