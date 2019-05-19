/**
 * 
 */
package com.huizhiwang.c6.filtermsg;


public class App{

  public static void main(String[] args) throws Exception {
    FilterHelper fh = new FilterHelper();
    //监听新块事件
    fh.blockFilter();
    //监听新合约事件
    fh.txFilter();
    //监听待定合约事件
    fh.pendingTxFilter();
    //用一个交易触发监听
    fh.demoTx();
    //监听合约事件
    Voting voting = fh.deployContract(0);
    fh.eventFilter(voting);
  } 
}
