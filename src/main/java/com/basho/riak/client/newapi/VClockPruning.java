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

/**
 * The VClock prune settings for a {@link Bucket}. 
 * <p> 
 * The default values are:
 * <ul>
 * <li>small: 10</li>
 * <li>big: 50</li>
 * <li>young: 20 (seconds)</li>
 * <li>old: 86400 (seconds)</li>
 * </ul>
 * see <a href="see https://help.basho.com/entries/448442-how-does-riak-control-the-growth-of-vector-clocks">How does Riak control the growth of vector clocks?</a> for more details.
 * </p>
 * 
 * @Immutable
 * 
 * @see VClockPruneBuilder
 * @author russell
 * 
 */
public class VClockPruning {

    private final int small;
    private final int big;
    private final long young;
    private final long old;

    public VClockPruning(int small, int big, long young, long old) {
        if (small > big) {
            throw new IllegalArgumentException("small must be smaller than big");
        }
        if (young > old) {
            throw new IllegalArgumentException("young must be smaller than old");
        }
        this.old = old;
        this.young = young;
        this.big = big;
        this.small = small;
    }

    /**
     * @return the minimum size of VCLock list below which pruning will not occur.
     */
    public int getSmall() {
        return small;
    }

    /**
     * @return the maximum length of the VClock list above which pruning will occur.
     */
    public int getBig() {
        return big;
    }

    /**
     * @return the age (in seconds) below which VClocks will not be pruned.
     */
    public long getYoungSeconds() {
        return young;
    }

    /**
     * @return the age (in seconds) after which a VClock will be pruned.
     */
    public long getOldSeconds() {
        return old;
    }

   
    public VClockPruneBuilder fromMe() {
        return VClockPruneBuilder.from(this);
    }

    public VClockPruneBuilder newBuilder() {
        return new VClockPruneBuilder();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + big;
        result = prime * result + (int) (old ^ (old >>> 32));
        result = prime * result + small;
        result = prime * result + (int) (young ^ (young >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof VClockPruning)) {
            return false;
        }
        VClockPruning other = (VClockPruning) obj;
        if (big != other.big) {
            return false;
        }
        if (old != other.old) {
            return false;
        }
        if (small != other.small) {
            return false;
        }
        if (young != other.young) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override public String toString() {
        return String.format("VClockPruneSettings [small=%s, big=%s, young=%s, old=%s]",
                             small, big, young, old);
    }
}
