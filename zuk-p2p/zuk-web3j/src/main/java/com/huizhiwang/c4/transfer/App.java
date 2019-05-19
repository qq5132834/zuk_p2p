/**
 * 
 */
package com.huizhiwang.c4.transfer;

public class App{

  public static void main(String[] args) throws Exception {
    TxHelper TH = new TxHelper();
    //获取节点账户余额
    TH.getNodeAccountBalance(0);
    //货币单位换算
    TH.convertUnit();
    //提交普通交易
    TH.transactionOnly(0,1,100);
    //等待交易收据
    TH.transactionWithReceipt(0,1,100);
    //设置gas参数
    TH.transactionWithGas(1,2,100);
    //写入任意数据
    TH.transactionWithData(3,"this is a demo!");
    //从节点账户向钱包账户转账
    TH.transferToWallet();
    //提交裸交易
    TH.rawTransaction();
    //使用Transfer类转账
    TH.transferMoney(3,4,100);
    //使用裸交易管理器
    TH.transferMoneyRaw(6,100);
  } 
}