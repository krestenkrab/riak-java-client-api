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

import java.util.Collection;
import java.util.Iterator;

import com.basho.riak.client.newapi.cap.Quorum;
import com.basho.riak.client.newapi.query.NamedErlangFunction;
import com.basho.riak.client.newapi.query.NamedFunction;

/**
 * Models a Riak Bucket.
 * 
 * You can fetch a bucket from Riak, or create one and then load it's properties
 * etc from Riak.
 * 
 * @author russell
 * 
 */
public class DefaultBucket implements Bucket {

    private final String name;
    private final RiakClient riakClient;

    // TODO how to populate in a thread safe and lazy way?
    private volatile BucketProperties properties;

    public DefaultBucket(final String name, final RiakClient riakClient) {
        this.name = name;
        this.riakClient = riakClient;
    }

    DefaultBucket(String name, final RiakClient riakClient, BucketProperties properties) {
        this(name, riakClient);
        this.properties = properties;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public boolean isAllowSiblings() {
        return properties.getAllowSiblings();
    }

    public boolean isLastWriteWins() {
        return properties.getLastWriteWins();
    }

    public int getNVal() {
        return properties.getNVal();
    }

    public String getBackend() {
        return properties.getBackend();
    }

    public int getSmallVClock() {
        return properties.getSmallVClock();
    }

    public int getBigVClock() {
        return properties.getBigVClock();
    }

    public long getYoungVClock() {
        return properties.getYoungVClock();
    }

    public long getOldVClock() {
        return properties.getOldVClock();
    }

    public Collection<NamedFunction> getPrecommitHooks() {
        return properties.getPreCommitHooks();
    }

    public Collection<NamedErlangFunction> getPostCommitHooks() {
        return properties.getPostCommitHooks();
    }

    public NamedErlangFunction getChashKeyFunction() {
        return properties.getChashKeyFunction();
    }

    public NamedErlangFunction getLinkWalkFunction() {
        return properties.getLinkWalkFunction();
    }

    public Quorum getR() {
        return properties.getR();
    }

    public Quorum getW() {
        return properties.getW();
    }

    public Quorum getRW() {
        return properties.getRW();
    }

    public Quorum getDW() {
        return properties.getDW();
    }

    public BucketBuilder fromMe() {
        return DefaultBucket.from(this);
    }

    public static BucketBuilder from(DefaultBucket bucket) {
        return BucketBuilder.from(bucket.name, bucket.riakClient, bucket.properties);
    }

    public Iterator<String> iterator() {
        // TODO stream the keys from a bucket
        return null;
    }
}
