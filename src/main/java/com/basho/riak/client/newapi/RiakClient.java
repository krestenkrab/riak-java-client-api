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
package com.basho.riak.client.newapi;

import java.io.IOException;
import java.util.Collection;

import com.basho.riak.client.newapi.cap.DeleteCAP;
import com.basho.riak.client.newapi.cap.FetchCAP;
import com.basho.riak.client.newapi.cap.StoreCAP;
import com.basho.riak.client.newapi.query.MapReduceResult;
import com.basho.riak.client.newapi.query.MapReduceSpec;
import com.basho.riak.client.newapi.query.WalkResult;
import com.basho.riak.client.newapi.query.WalkSpec;

/**
 * A client to a Riak node
 * 
 * @author russell
 * 
 */
public interface RiakClient extends Iterable<Bucket> {

    // server misc.
    void setClientId(String clientId) throws IOException;

    void ping() throws IOException;

    RiakServerInfo getServerInfo() throws IOException;

    // buckets
    Bucket fetchBucket(String bucketName) throws IOException;

    void updateBucket(final Bucket bucket) throws IOException;

    // store
    void store(final RiakObject riakObject) throws IOException;

    void store(final RiakObject riakObject, final StoreMeta storeMeta) throws IOException;

    RiakObject storeAndReturn(final RiakObject riakObject) throws IOException;

    RiakObject storeAndReturn(final RiakObject riakObject, final StoreMeta storeMeta);

    // bulk store
    void store(final Iterable<RiakObject> riakObjects);

    void store(final Iterable<RiakObject> riakObjects, final StoreCAP storeCAP);

    Collection<RiakObject> storeAndReturn(final Iterable<RiakObject> riakObjects);

    Collection<RiakObject> storeAndReturn(final Iterable<RiakObject> riakObjects, final StoreMeta storeMeta);

    // fetch
    RiakObject fetch(final RiakObject riakObject);

    RiakObject fetch(final RiakObject riakObject, final FetchCAP fetchCAP);

    RiakObject fetch(final Bucket bucket, String key);

    RiakObject fetch(final Bucket bucket, String key, final FetchCAP fetchCAP);

    RiakObject fetch(final String bucket, String key);

    RiakObject fetch(final String bucket, String key, final FetchCAP fetchCAP);

    // delete
    void delete(final RiakObject riakObject);

    void delete(final RiakObject riakObject, final DeleteCAP deleteCAP);

    void delete(final Bucket bucket, String key);

    void delete(final DefaultBucket bucket, String key, final DeleteCAP deleteCAP);

    void delete(final String bucket, String key);

    void delete(final String bucket, String key, final DeleteCAP deleteCAP);

    // query - links
    WalkResult walk(final RiakObject startObject, final WalkSpec walkSpec);

    // query - m/r
    MapReduceResult mapReduce(final MapReduceSpec mapReduceSpec);
}
