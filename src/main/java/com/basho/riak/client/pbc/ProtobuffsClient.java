/*
 * This file is provided to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.basho.riak.client.pbc;

import java.io.IOException;

import static com.google.protobuf.ByteString.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.basho.riak.client.newapi.Bucket;
import com.basho.riak.client.newapi.BucketBuilder;
import com.basho.riak.client.newapi.DefaultBucket;
import com.basho.riak.client.newapi.RiakClient;
import com.basho.riak.client.newapi.RiakObject;
import com.basho.riak.client.newapi.RiakObjectBuilder;
import com.basho.riak.client.newapi.RiakServerInfo;
import com.basho.riak.client.newapi.StoreMeta;
import com.basho.riak.client.newapi.cap.DeleteCAP;
import com.basho.riak.client.newapi.cap.FetchCAP;
import com.basho.riak.client.newapi.cap.StoreCAP;
import com.basho.riak.client.newapi.query.MapReduceResult;
import com.basho.riak.client.newapi.query.MapReduceSpec;
import com.basho.riak.client.newapi.query.WalkResult;
import com.basho.riak.client.newapi.query.WalkSpec;
import com.basho.riak.pbc.BucketProperties;
import com.basho.riak.pbc.IRequestMeta;
import com.basho.riak.pbc.RequestMeta;
import com.google.protobuf.ByteString;

/**
 * @author russell
 * 
 */
public class ProtobuffsClient implements RiakClient {

    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final int DEFAULT_PORT = 8087;

    private final com.basho.riak.pbc.RiakClient delegate;

    /**
     * Create a protobufs client that talks to at DEFAULT_HOST on DEFAULT_PORT
     * 
     * @throws IOException
     */
    public ProtobuffsClient() throws IOException {
        delegate = new com.basho.riak.pbc.RiakClient(DEFAULT_HOST, DEFAULT_PORT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<Bucket> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#setClientId(java.lang.String)
     */
    public void setClientId(String clientId) throws IOException {
        delegate.setClientID(clientId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#ping()
     */
    public void ping() throws IOException {
        delegate.ping();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#getServerInfo()
     */
    public RiakServerInfo getServerInfo() throws IOException {
        Map<String, String> si = delegate.getServerInfo();
        return RiakServerInfo.fromMap(si);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#fetchBucket(java.lang.String)
     */
    public Bucket fetchBucket(String bucket) throws IOException {
        BucketProperties props = delegate.getBucketProperties(copyFromUtf8(bucket));
        return new BucketBuilder(bucket, this).nVal(props.getNValue()).allowSiblings(props.getAllowMult()).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#updateBucket(com.basho.riak.client
     * .newapi.Bucket)
     */
    public void updateBucket(Bucket bucket) throws IOException {
        ByteString bucketName = copyFromUtf8(bucket.getName());
        BucketProperties props = new BucketProperties();
        props.nValue(bucket.getNVal());
        props.allowMult(bucket.isAllowSiblings());
        delegate.setBucketProperties(bucketName, props);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#store(com.basho.riak.client.newapi
     * .RiakObject)
     */
    public void store(RiakObject riakObject) throws IOException {
        com.basho.riak.pbc.RiakObject ro = makePBCRiakObject(riakObject);
        delegate.store(ro);
    }

    /**
     * @param riakObject
     * @return
     */
    private com.basho.riak.pbc.RiakObject makePBCRiakObject(RiakObject riakObject) {
        String bucket = riakObject.getBucketName();
        String key = riakObject.getKey();
        String content = riakObject.getValue();
        com.basho.riak.pbc.RiakObject ro = new com.basho.riak.pbc.RiakObject(bucket, key, content);
        return ro;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#store(com.basho.riak.client.newapi
     * .RiakObject, com.basho.riak.client.newapi.StoreCAP)
     */
    public void store(RiakObject riakObject, StoreMeta storeMeta) throws IOException {
        IRequestMeta requestMeta = makeRequestMeta(storeMeta).returnBody(false);
        delegate.store(makePBCRiakObject(riakObject), requestMeta);
    }

    /**
     * @param StoreMeta
     * @return IRequestMeta
     */
    private IRequestMeta makeRequestMeta(StoreMeta storeMeta) {
        IRequestMeta requestMeta = new RequestMeta();
        if (storeMeta.hasDW()) {
            requestMeta.dw(storeMeta.getDw());
        }

        if (storeMeta.hasW()) {
            requestMeta.w(storeMeta.getW());
        }

        if (storeMeta.hasContentType()) {
            requestMeta.contentType(storeMeta.getContentType());
        }

        return requestMeta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#storeAndReturn(com.basho.riak
     * .client.newapi.RiakObject)
     */
    public RiakObject storeAndReturn(RiakObject riakObject) throws IOException {
        com.basho.riak.pbc.RiakObject[] ros = delegate.store(makePBCRiakObject(riakObject), 
                                                             new RequestMeta().returnBody(true));
        
        boolean hasConflict = ros.length > 1;
        
        RiakObjectBuilder builder = RiakObjectBuilder.newBuilder(ros[0].getBucket(), ros[0].getKey());
        
        
        return builder.build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#storeAndReturn(com.basho.riak
     * .client.newapi.RiakObject, com.basho.riak.client.newapi.StoreCAP)
     */
    public RiakObject storeAndReturn(RiakObject riakObject, StoreMeta storeMeta) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#store(java.lang.Iterable)
     */
    public void store(Iterable<RiakObject> riakObjects) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#store(java.lang.Iterable,
     * com.basho.riak.client.newapi.StoreCAP)
     */
    public void store(Iterable<RiakObject> riakObjects, StoreCAP storeCAP) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#storeAndReturn(java.lang.Iterable
     * )
     */
    public Collection<RiakObject> storeAndReturn(Iterable<RiakObject> riakObjects) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#storeAndReturn(java.lang.Iterable
     * , com.basho.riak.client.newapi.StoreCAP)
     */
    public Collection<RiakObject> storeAndReturn(Iterable<RiakObject> riakObjects, StoreMeta storeMeta) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#fetch(com.basho.riak.client.newapi
     * .RiakObject)
     */
    public RiakObject fetch(RiakObject riakObject) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#fetch(com.basho.riak.client.newapi
     * .RiakObject, com.basho.riak.client.newapi.FetchCAP)
     */
    public RiakObject fetch(RiakObject riakObject, FetchCAP fetchCAP) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#fetch(com.basho.riak.client.newapi
     * .Bucket, java.lang.String)
     */
    public RiakObject fetch(Bucket bucket, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#fetch(com.basho.riak.client.newapi
     * .Bucket, java.lang.String, com.basho.riak.client.newapi.FetchCAP)
     */
    public RiakObject fetch(Bucket bucket, String key, FetchCAP fetchCAP) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#fetch(java.lang.String,
     * java.lang.String)
     */
    public RiakObject fetch(String bucket, String key) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#fetch(java.lang.String,
     * java.lang.String, com.basho.riak.client.newapi.FetchCAP)
     */
    public RiakObject fetch(String bucket, String key, FetchCAP fetchCAP) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#delete(com.basho.riak.client.
     * newapi.RiakObject)
     */
    public void delete(RiakObject riakObject) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#delete(com.basho.riak.client.
     * newapi.RiakObject, com.basho.riak.client.newapi.DeleteCAP)
     */
    public void delete(RiakObject riakObject, DeleteCAP deleteCAP) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#delete(com.basho.riak.client.
     * newapi.Bucket, java.lang.String)
     */
    public void delete(Bucket bucket, String key) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#delete(com.basho.riak.client.
     * newapi.DefaultBucket, java.lang.String,
     * com.basho.riak.client.newapi.DeleteCAP)
     */
    public void delete(DefaultBucket bucket, String key, DeleteCAP deleteCAP) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#delete(java.lang.String,
     * java.lang.String)
     */
    public void delete(String bucket, String key) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.basho.riak.client.newapi.RiakClient#delete(java.lang.String,
     * java.lang.String, com.basho.riak.client.newapi.DeleteCAP)
     */
    public void delete(String bucket, String key, DeleteCAP deleteCAP) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#walk(com.basho.riak.client.newapi
     * .RiakObject, com.basho.riak.client.newapi.query.WalkSpec)
     */
    public WalkResult walk(RiakObject startObject, WalkSpec walkSpec) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.basho.riak.client.newapi.RiakClient#mapReduce(com.basho.riak.client
     * .newapi.query.MapReduceSpec)
     */
    public MapReduceResult mapReduce(MapReduceSpec mapReduceSpec) {
        // TODO Auto-generated method stub
        return null;
    }

}
