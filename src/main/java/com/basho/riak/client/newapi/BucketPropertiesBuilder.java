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

import java.util.ArrayList;
import java.util.Collection;

import com.basho.riak.client.newapi.cap.CAPQuoraBuilder;
import com.basho.riak.client.newapi.cap.Quorum;
import com.basho.riak.client.newapi.query.NamedErlangFunction;
import com.basho.riak.client.newapi.query.NamedFunction;
import com.basho.riak.client.newapi.vclock.VClockPruneBuilder;

/**
 * For creating/updating {@link BucketProperties}. Not thread safe, use from a
 * single thread only.
 * 
 * @author russell
 * 
 */
public class BucketPropertiesBuilder implements Builder<BucketProperties> {
    private Boolean allowSiblings;
    private Boolean lastWriteWins;
    private Integer nVal;
    private String backend;
    private VClockPruneBuilder vClockPruneBuilder = new VClockPruneBuilder();
    private Collection<NamedFunction> preCommitHooks;
    private Collection<NamedErlangFunction> postCommitHooks;
    private CAPQuoraBuilder capQuoraBuilder = new CAPQuoraBuilder();
    private NamedErlangFunction chashKeyFunction;
    private NamedErlangFunction linkWalkFunction;

    public BucketPropertiesBuilder() {}

    public BucketProperties build() {
        return new BucketProperties(allowSiblings, lastWriteWins, nVal, backend, vClockPruneBuilder.build(),
                                    preCommitHooks, postCommitHooks, capQuoraBuilder.build(), chashKeyFunction,
                                    linkWalkFunction);
    }

    public BucketPropertiesBuilder allowSiblings(boolean allowSiblings) {
        this.allowSiblings = allowSiblings;
        return this;
    }

    public BucketPropertiesBuilder lastWriteWins(boolean lastWriteWins) {
        this.lastWriteWins = lastWriteWins;
        return this;
    }

    public BucketPropertiesBuilder nVal(int nVal) {
        this.nVal = nVal;
        return this;
    }

    public BucketPropertiesBuilder backend(String backend) {
        this.backend = backend;
        return this;
    }

    public BucketPropertiesBuilder preCommitHooks(Collection<NamedFunction> preCommitHooks) {
        this.preCommitHooks = preCommitHooks;
        return this;
    }

    public BucketPropertiesBuilder addPreCommitHook(NamedFunction preCommitHook) {
        if (this.preCommitHooks == null) {
            this.preCommitHooks = new ArrayList<NamedFunction>();
        }
        this.preCommitHooks.add(preCommitHook);
        return this;
    }

    public BucketPropertiesBuilder postCommitHooks(Collection<NamedErlangFunction> postCommitHooks) {
        this.postCommitHooks = postCommitHooks;
        return this;
    }

    public BucketPropertiesBuilder addPostCommitHook(NamedErlangFunction postCommitHook) {
        if (this.postCommitHooks == null) {
            this.postCommitHooks = new ArrayList<NamedErlangFunction>();
        }
        this.preCommitHooks.add(postCommitHook);
        return this;
    }

    public BucketPropertiesBuilder chashKeyFunction(NamedErlangFunction chashKeyFunction) {
        this.chashKeyFunction = chashKeyFunction;
        return this;
    }

    public BucketPropertiesBuilder linkWalkFunction(NamedErlangFunction linkWalkFunction) {
        this.linkWalkFunction = linkWalkFunction;
        return this;
    }

    /**
     * @param smallVClock
     * @return
     */
    public BucketPropertiesBuilder smallVClock(int smallVClock) {
        vClockPruneBuilder.smallVClock(smallVClock);
        return this;
    }

    /**
     * @param bigVClock
     * @return
     */
    public BucketPropertiesBuilder bigVClock(int bigVClock) {
        vClockPruneBuilder.bigVClock(bigVClock);
        return this;
    }

    /**
     * @param youngVClock
     * @return
     */
    public BucketPropertiesBuilder youngVClock(long youngVClock) {
        vClockPruneBuilder.youngVClock(youngVClock);
        return this;
    }

    /**
     * @param oldVClock
     * @return
     */
    public BucketPropertiesBuilder oldVClock(long oldVClock) {
        vClockPruneBuilder.oldVClock(oldVClock);
        return this;
    }

    /**
     * @param r
     * @return
     */
    public BucketPropertiesBuilder r(Quorum r) {
        capQuoraBuilder.r(r);
        return this;
    }

    /**
     * @param w
     * @return
     */
    public BucketPropertiesBuilder w(Quorum w) {
        capQuoraBuilder.w(w);
        return this;
    }

    /**
     * @param rw
     * @return
     */
    public BucketPropertiesBuilder rw(Quorum rw) {
        capQuoraBuilder.rw(rw);
        return this;
    }

    /**
     * @param dw
     * @return
     */
    public BucketPropertiesBuilder dw(Quorum dw) {
        capQuoraBuilder.dw(dw);
        return this;
    }

    /**
     * Create a builder initialized with the passed {@link BucketProperties}'
     * values
     * 
     * @param properties
     *            the {@link BucketProperties} to copy
     * @return a builder with values copied from properties.
     */
    public static BucketPropertiesBuilder from(BucketProperties properties) {
        BucketPropertiesBuilder builder = new BucketPropertiesBuilder();

        builder.allowSiblings = properties.getAllowSiblings();
        builder.lastWriteWins = properties.getLastWriteWins();
        builder.nVal = properties.getNVal();
        builder.backend = properties.getBackend();
        builder.vClockPruneBuilder = VClockPruneBuilder.from(properties);
        builder.preCommitHooks = new ArrayList<NamedFunction>(properties.getPreCommitHooks());
        builder.postCommitHooks = new ArrayList<NamedErlangFunction>(properties.getPostCommitHooks());
        builder.chashKeyFunction = properties.getChashKeyFunction();
        builder.linkWalkFunction = properties.getLinkWalkFunction();
        builder.capQuoraBuilder = CAPQuoraBuilder.from(properties);
        return builder;
    }
}
