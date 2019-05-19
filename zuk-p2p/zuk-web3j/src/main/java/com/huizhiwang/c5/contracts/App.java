/**
 * 
 */
package com.huizhiwang.c5.contracts;



public class App{

  public static void main(String[] args) throws Exception {
    ContractHelper ch = new ContractHelper();
    //部署合约
    Voting c1 = ch.deployContract(1);
    //载入合约
    Voting c2 = ch.loadContract(c1.getContractAddress(),3);
    //调用合约方法
    ch.callContractMethod(c1,"Tommy");
    ch.callContractMethod(c1,"Jerry");
    ch.callContractMethod(c2,"Jerry");
    ch.callContractMethod(c1,"Micky");
  } 
}