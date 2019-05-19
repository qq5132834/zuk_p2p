package com.huizhiwang.c5.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class Voting_sol_Voting extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506040516104173803806104178339810180604052602081101561003357600080fd5b81019080805164010000000081111561004b57600080fd5b8281019050602081018481111561006157600080fd5b815185602082028301116401000000008211171561007e57600080fd5b5050929190505050806001908051906020019061009c9291906100a3565b5050610115565b8280548282559060005260206000209081019282156100df579160200282015b828111156100de5782518255916020019190600101906100c3565b5b5090506100ec91906100f0565b5090565b61011291905b8082111561010e5760008160009055506001016100f6565b5090565b90565b6102f3806101246000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632b38cd961461005c5780633477ee2e146100a457806335154986146100e6578063392e667814610114578063b13479081461015a575b600080fd5b6100886004803603602081101561007257600080fd5b81019080803590602001909291905050506101a2565b604051808260ff1660ff16815260200191505060405180910390f35b6100d0600480360360208110156100ba57600080fd5b81019080803590602001909291905050506101c2565b6040518082815260200191505060405180910390f35b610112600480360360208110156100fc57600080fd5b81019080803590602001909291905050506101e3565b005b6101406004803603602081101561012a57600080fd5b8101908080359060200190929190505050610236565b604051808215151515815260200191505060405180910390f35b6101866004803603602081101561017057600080fd5b810190808035906020019092919050505061028c565b604051808260ff1660ff16815260200191505060405180910390f35b60006020528060005260406000206000915054906101000a900460ff1681565b600181815481106101cf57fe5b906000526020600020016000915090505481565b6101ec81610236565b6101f557600080fd5b600160008083815260200190815260200160002060008282829054906101000a900460ff160192506101000a81548160ff021916908360ff16021790555050565b600080600090505b60018054905081101561028157826001828154811061025957fe5b90600052602060002001541415610274576001915050610287565b808060010191505061023e565b50600090505b919050565b600061029782610236565b6102a057600080fd5b60008083815260200190815260200160002060009054906101000a900460ff16905091905056fea165627a7a72305820289091a9bda8c49057cebaf44d853cc2f3107b72feff5153ef67f6ff741ffc0e0029";

    public static final String FUNC_VOTES = "votes";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_VOTEFOR = "voteFor";

    public static final String FUNC_VALIDCANDIDATE = "validCandidate";

    public static final String FUNC_GETVOTESFOR = "getVotesFor";

    @Deprecated
    protected Voting_sol_Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

//    protected Voting_sol_Voting(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
//    }

    @Deprecated
    protected Voting_sol_Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Voting_sol_Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> votes(byte[] param0) {
        final Function function = new Function(FUNC_VOTES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<byte[]> candidates(BigInteger param0) {
        final Function function = new Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> voteFor(byte[] candidate) {
        final Function function = new Function(
                FUNC_VOTEFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(candidate)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> validCandidate(byte[] candidate) {
        final Function function = new Function(FUNC_VALIDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(candidate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<BigInteger> getVotesFor(byte[] candidate) {
        final Function function = new Function(FUNC_GETVOTESFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(candidate)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Voting_sol_Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting_sol_Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Voting_sol_Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting_sol_Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    public static Voting_sol_Voting load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new Voting_sol_Voting(contractAddress, web3j, credentials, contractGasProvider);
//    }

    public static Voting_sol_Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Voting_sol_Voting(contractAddress, web3j, transactionManager, contractGasProvider);
    }

//    public static RemoteCall<Voting_sol_Voting> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, List<byte[]> candidateNames) {
//        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
//                        org.web3j.abi.datatypes.generated.Bytes32.class,
//                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
//        return deployRemoteCall(Voting_sol_Voting.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
//    }

//    public static RemoteCall<Voting_sol_Voting> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, List<byte[]> candidateNames) {
//        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
//                        org.web3j.abi.datatypes.generated.Bytes32.class,
//                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
//        return deployRemoteCall(Voting_sol_Voting.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
//    }

//    @Deprecated
//    public static RemoteCall<Voting_sol_Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> candidateNames) {
//        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
//                        org.web3j.abi.datatypes.generated.Bytes32.class,
//                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
//        return deployRemoteCall(Voting_sol_Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
//    }

//    @Deprecated
//    public static RemoteCall<Voting_sol_Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> candidateNames) {
//        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
//                        org.web3j.abi.datatypes.generated.Bytes32.class,
//                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
//        return deployRemoteCall(Voting_sol_Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
//    }
}
