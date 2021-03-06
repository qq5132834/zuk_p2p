/**
 * 
 */
package com.huizhiwang.c6.filtermsg;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Voting extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060405161032d38038061032d83398101604052805101805161003a906001906020840190610041565b50506100ab565b82805482825590600052602060002090810192821561007e579160200282015b8281111561007e5782518255602090920191600190910190610061565b5061008a92915061008e565b5090565b6100a891905b8082111561008a5760008155600101610094565b90565b610273806100ba6000396000f30060806040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632b38cd9681146100715780633477ee2e1461009f57806335154986146100c9578063392e6678146100e3578063b13479081461010f575b600080fd5b34801561007d57600080fd5b50610089600435610127565b6040805160ff9092168252519081900360200190f35b3480156100ab57600080fd5b506100b760043561013c565b60408051918252519081900360200190f35b3480156100d557600080fd5b506100e160043561015b565b005b3480156100ef57600080fd5b506100fb6004356101ce565b604080519115158252519081900360200190f35b34801561011b57600080fd5b5061008960043561021b565b60006020819052908152604090205460ff1681565b600180548290811061014a57fe5b600091825260209091200154905081565b610164816101ce565b151561016f57600080fd5b60008181526020818152604091829020805460ff8082166001011660ff19909116179055815133815290810183905281517fc1eff9d9e2ab8a2b29706e0c2818cd78972e60f1ce84c268a77005b0bece97c4929181900390910190a150565b6000805b6001548110156102105760018054849190839081106101ed57fe5b60009182526020909120015414156102085760019150610215565b6001016101d2565b600091505b50919050565b6000610226826101ce565b151561023157600080fd5b5060009081526020819052604090205460ff16905600a165627a7a723058209a9acb2d24af72ea402b4bd2dea83846c2c01aebd4148e2276af8bb2b73803bd0029";

    public static final String FUNC_VOTES = "votes";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_VOTEFOR = "voteFor";

    public static final String FUNC_VALIDCANDIDATE = "validCandidate";

    public static final String FUNC_GETVOTESFOR = "getVotesFor";

    public static final Event VOTE_EVENT = new Event("Vote", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected Voting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Voting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

    public static RemoteCall<Voting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Voting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<byte[]> candidateNames) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Bytes32>(
                        org.web3j.abi.Utils.typeMap(candidateNames, org.web3j.abi.datatypes.generated.Bytes32.class))));
        return deployRemoteCall(Voting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<VoteEventResponse> getVoteEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTE_EVENT, transactionReceipt);
        ArrayList<VoteEventResponse> responses = new ArrayList<VoteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoteEventResponse typedResponse = new VoteEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.candidate = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<VoteEventResponse> voteEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, VoteEventResponse>() {
            @Override
            public VoteEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTE_EVENT, log);
                VoteEventResponse typedResponse = new VoteEventResponse();
                typedResponse.log = log;
                typedResponse.voter = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.candidate = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<VoteEventResponse> voteEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTE_EVENT));
        return voteEventObservable(filter);
    }

    public static Voting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Voting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Voting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class VoteEventResponse {
        public Log log;

        public String voter;

        public byte[] candidate;
    }
}

