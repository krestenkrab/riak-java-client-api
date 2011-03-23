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
package com.basho.riak.client.newapi.vclock;

import com.basho.riak.client.newapi.BucketProperties;
import com.basho.riak.client.newapi.DefaultBucket;

/**
 * The VClock prune used internally by {@link DefaultBucket}.
 * <p>
 * see <a href=
 * "see https://help.basho.com/entries/448442-how-does-riak-control-the-growth-of-vector-clocks">How does Riak control the growth of vector clocks?</a> for more details.
 * </p>
 * 
 * Immutable.
 * 
 * @see VClockPruneBuilder
 * @author russell
 * 
 */
public class VClockPruning {

    private final Integer small;
    private final Integer big;
    private final Long young;
    private final Long old;

    /**
     * Create a set of VCLock pruning settings.
     * <p>
     * Any of the parameters may be set to null and the defaults on the server
     * will stand. If small and big are both not null then small must be smaller
     * than big. If young and old are both non-null then young must be smaller
     * than old.
     * </p>
     * 
     * @param small
     *            the length below which a VClock will not be pruned
     * @param big
     *            the length above which a VClock will be pruned
     * @param young
     *            the the timestamp younger than which a VClock will not be
     *            pruned
     * @param old
     *            the timestamp older than which a VCLock will be pruned
     * 
     */
    public VClockPruning(Integer small, Integer big, Long young, Long old) {
        if ((small != null && big != null) && (small > big)) {
            throw new IllegalArgumentException("small must be smaller than big");
        }
        if ((young != null && old != null) && (young > old)) {
            throw new IllegalArgumentException("young must be smaller than old");
        }
        this.old = old;
        this.young = young;
        this.big = big;
        this.small = small;
    }

    /**
     * @return the minimum size of VCLock list below which pruning will not
     *         occur, if set, or null otherwise.
     */
    public Integer getSmall() {
        return small;
    }

    /**
     * @return the maximum length of the VClock list above which pruning will
     *         occur.
     */
    public Integer getBig() {
        return big;
    }

    /**
     * @return the age (in seconds) below which VClocks will not be pruned.
     */
    public Long getYoungSeconds() {
        return young;
    }

    /**
     * @return the age (in seconds) after which a VClock will be pruned.
     */
    public Long getOldSeconds() {
        return old;
    }

    /**
     * 
     * @return a {@link VClockPruneBuilder} initialized to this instances
     *         values.
     */
    public VClockPruneBuilder fromMe() {
        return VClockPruneBuilder.from(this);
    }

    /**
     * @return a new {@link VClockPruneBuilder}
     */
    public VClockPruneBuilder newBuilder() {
        return new VClockPruneBuilder();
    }

    /**
     * Copy the contents of vClockPruning into a new builder.
     * 
     * @param vClockPruning
     * @return a {@link VClockPruneBuilder} initialized from vClockPruning
     */
    public static VClockPruneBuilder from(VClockPruning vClockPruning) {
        return VClockPruneBuilder.from(vClockPruning);
    }

    /**
     * @param properties
     *            the {@link BucketProperties} whose r, w, rw, dw properties
     *            should be used to create a VClockPruning.
     * @return a VClockPruning with same r, w, rw, dw as the passed
     *         {@link BucketProperties}.
     */
    public static VClockPruning from(BucketProperties properties) {
        return VClockPruneBuilder.from(properties).build();
    }

}
