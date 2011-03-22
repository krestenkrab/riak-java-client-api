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
 * Build a {@link VClockPruning}.
 * 
 * @author russell
 * 
 */
public class VClockPruneBuilder implements Builder<VClockPruning> {

    private int smallVClock = 10;
    private int bigVClock = 50;
    private long youngVClock = 20;
    private long oldVClock = 86400;

    public VClockPruning build() {
        return new VClockPruning(smallVClock, bigVClock, youngVClock, oldVClock);
    }

    public static VClockPruneBuilder from(VClockPruning prototype) {
        final VClockPruneBuilder builder = new VClockPruneBuilder();
        builder.smallVClock = prototype.getSmall();
        builder.bigVClock = prototype.getBig();
        builder.youngVClock = prototype.getYoungSeconds();
        builder.oldVClock = prototype.getOldSeconds();
        return builder;
    }

    public static VClockPruning defaultVClockSettings() {
        return new VClockPruneBuilder().build();
    }

    public VClockPruneBuilder smallVClock(int smallVClock) {
        this.smallVClock = smallVClock;
        return this;
    }

    public VClockPruneBuilder bigVClock(int bigVClock) {
        this.bigVClock = bigVClock;
        return this;
    }

    public VClockPruneBuilder youngVClock(long youngVClock) {
        this.youngVClock = youngVClock;
        return this;
    }

    public VClockPruneBuilder oldVClock(int oldVClock) {
        this.oldVClock = oldVClock;
        return this;
    }

}
