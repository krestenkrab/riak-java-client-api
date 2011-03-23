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
import java.util.Collections;

import com.basho.riak.client.newapi.cap.CAPQuora;
import com.basho.riak.client.newapi.cap.Quorum;
import com.basho.riak.client.newapi.query.NamedErlangFunction;
import com.basho.riak.client.newapi.query.NamedFunction;
import com.basho.riak.client.newapi.vclock.VClockPruning;

/**
 * 
 * @author russell
 */
public class BucketProperties {

    private final Boolean allowSiblings;
    private final Boolean lastWriteWins;
    private final Integer nVal;
    private final String backend;
    private final VClockPruning vClockPruning;
    private final Collection<NamedFunction> preCommitHooks;
    private final Collection<NamedErlangFunction> postCommitHooks;
    private final CAPQuora capQuora;
    private final NamedErlangFunction chashKeyFunction;
    private final NamedErlangFunction linkWalkFunction;

    /**
     * Use the builder.
     * 
     * @param allowSiblings
     *            are siblings allowed
     * @param lastWriteWins
     *            does the last write win, regardless of vclock
     * @param nVal
     *            how many replicas to store
     * @param backend
     *            if multi backend is configured, which named backend to use
     * @param vClockPruning
     *            settings for{@link VClockPruning}
     * @param preCommitHooks
     *            the set of functions to run before an object is stored
     * @param postCommitHooks
     *            the set of functions to run after an object is stored
     * @param capQuora
     *            the bucket level defaults for r, w, dw and rw {@link CAPQuora}
     * @param chashKeyFunction
     *            the key hashing function
     * @param linkWalkFunction
     *            the link walking function
     */
    BucketProperties(Boolean allowSiblings, Boolean lastWriteWins, Integer nVal, String backend,
            VClockPruning vClockPruning, Collection<NamedFunction> preCommitHooks,
            Collection<NamedErlangFunction> postCommitHooks, CAPQuora capQuora, NamedErlangFunction chashKeyFunction,
            NamedErlangFunction linkWalkFunction) {
        this.allowSiblings = allowSiblings;
        this.lastWriteWins = lastWriteWins;
        this.nVal = nVal;
        this.backend = backend;
        this.vClockPruning = vClockPruning;
        this.preCommitHooks = Collections.unmodifiableCollection(new ArrayList<NamedFunction>(preCommitHooks));
        this.postCommitHooks = Collections.unmodifiableCollection(new ArrayList<NamedErlangFunction>(postCommitHooks));
        this.capQuora = capQuora;
        this.chashKeyFunction = chashKeyFunction;
        this.linkWalkFunction = linkWalkFunction;
    }

    /**
     * @return the allowSiblings
     */
    public Boolean getAllowSiblings() {
        return allowSiblings;
    }

    /**
     * @return the lastWriteWins
     */
    public Boolean getLastWriteWins() {
        return lastWriteWins;
    }

    /**
     * @return the nVal
     */
    public Integer getNVal() {
        return nVal;
    }

    /**
     * @return the backend
     */
    public String getBackend() {
        return backend;
    }

    public int getSmallVClock() {
        return vClockPruning.getSmall();
    }

    public int getBigVClock() {
        return vClockPruning.getBig();
    }

    public long getYoungVClock() {
        return vClockPruning.getYoungSeconds();
    }

    public long getOldVClock() {
        return vClockPruning.getOldSeconds();
    }

    /**
     * @return the preCommitHooks
     */
    public Collection<NamedFunction> getPreCommitHooks() {
        return preCommitHooks;
    }

    /**
     * @return the postCommitHooks
     */
    public Collection<NamedErlangFunction> getPostCommitHooks() {
        return postCommitHooks;
    }

    public Quorum getR() {
        return capQuora.getR();
    }

    public Quorum getW() {
        return capQuora.getW();
    }

    public Quorum getRW() {
        return capQuora.getRW();
    }

    public Quorum getDW() {
        return capQuora.getDW();
    }

    /**
     * @return the chashKeyFunction
     */
    public NamedErlangFunction getChashKeyFunction() {
        return chashKeyFunction;
    }

    /**
     * @return the linkWalkFunction
     */
    public NamedErlangFunction getLinkWalkFunction() {
        return linkWalkFunction;
    }
    
    public BucketPropertiesBuilder fromMe() {
        return BucketProperties.from(this);
    }
    
    
    public static BucketPropertiesBuilder from(BucketProperties properties) {
        return BucketPropertiesBuilder.from(properties);
    }
}