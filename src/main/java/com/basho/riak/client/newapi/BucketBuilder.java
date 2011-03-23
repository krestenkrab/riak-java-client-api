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

import com.basho.riak.client.newapi.query.NamedErlangFunction;
import com.basho.riak.client.newapi.query.NamedFunction;

/**
 * 
 * @author russell
 * 
 */
public class BucketBuilder implements Builder<DefaultBucket> {

    private BucketPropertiesBuilder propertiesBuilder = new BucketPropertiesBuilder();
    private final String name;
    private RiakClient riakClient;

    public BucketBuilder(String name, RiakClient riakClient) {
        this.name = name;
        this.riakClient = riakClient;
    }
    
    private BucketBuilder(String name, RiakClient riakClient, BucketPropertiesBuilder propertiesBuilder) {
        this.name = name;
        this.riakClient = riakClient;
        this.propertiesBuilder = propertiesBuilder;
    }

    /**
     * @param allowSiblings
     * @return
     */
    public BucketBuilder allowSiblings(boolean allowSiblings) {
        propertiesBuilder.allowSiblings(allowSiblings);
        return this;
    }

    /**
     * @param backend
     * @return
     */
    public BucketBuilder backend(String backend) {
        propertiesBuilder.backend(backend);
        return this;
    }

    /**
     * @param chashKeyFunction
     * @return
     */
    public BucketBuilder chashKeyFunction(NamedErlangFunction chashKeyFunction) {
        propertiesBuilder.chashKeyFunction(chashKeyFunction);
        return this;
    }

    /**
     * @param nVal
     * @return
     */
    public BucketBuilder nVal(int nVal) {
        propertiesBuilder.nVal(nVal);
        return this;
    }

    /**
     * @param preCommitHooks
     * @return
     */
    public BucketBuilder preCommitHooks(Collection<NamedFunction> preCommitHooks) {
        propertiesBuilder.preCommitHooks(preCommitHooks);
        return this;
    }

    /**
     * @param preCommitHook
     * @return
     */
    public BucketBuilder addPreCommitHook(NamedFunction preCommitHook) {
        propertiesBuilder.addPreCommitHook(preCommitHook);
        return this;
    }

    /**
     * @param postCommitHooks
     * @return
     */
    public BucketBuilder postCommitHooks(Collection<NamedErlangFunction> postCommitHooks) {
        propertiesBuilder.postCommitHooks(postCommitHooks);
        return this;
    }

    /**
     * @param postCommitHook
     * @return
     */
    public BucketBuilder addPostCommitHook(NamedErlangFunction postCommitHook) {
        propertiesBuilder.addPostCommitHook(postCommitHook);
        return this;
    }

    /**
     * @param lastWriteWins
     * @return
     */
    public BucketBuilder lastWriteWins(boolean lastWriteWins) {
        propertiesBuilder.lastWriteWins(lastWriteWins);
        return this;
    }

    /**
     * @param linkWalkFunction
     * @return
     */
    public BucketBuilder linkWalkFunction(NamedErlangFunction linkWalkFunction) {
        propertiesBuilder.linkWalkFunction(linkWalkFunction);
        return this;
    }
    
    public DefaultBucket build() {
        return new DefaultBucket(name, riakClient, propertiesBuilder.build());
    }

    public static BucketBuilder from(DefaultBucket bucket) {
        return DefaultBucket.from(bucket);
    }

    static BucketBuilder from(String name, RiakClient riakClient, BucketProperties properties) {
        BucketBuilder builder = new BucketBuilder(name, riakClient, BucketPropertiesBuilder.from(properties));
        return builder;
    }

}
