/**
 * 
 */
package com.huizhiwang.c3.account;

public class App{

  public static void main(String[] args) throws Exception {
    AccountHelper AH = new AccountHelper();
    //创建密钥对
    String[] tuple = AH.newAccount();
    //导入私钥
    String[] tuple2 = AH.importPrivateKey(tuple[0]);
    //创建钱包文件
    String walletFileName = AH.newWalletFile("123");
    //载入钱包文件，创建账户凭证
    AH.loadWalletFile("123",walletFileName);
    //查看节点账户
    AH.getNodeAccounts();
  }  
}