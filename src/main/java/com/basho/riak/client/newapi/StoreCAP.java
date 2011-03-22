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
 * Per request tunable cap settings for a store operation.
 * 
 * @author russell
 * 
 */
public final class StoreCAP {
    private final Integer w;
    private final Integer dw;

    public StoreCAP(int w, int dw) {
        this(Integer.valueOf(w), Integer.valueOf(dw));
    }

    private StoreCAP(Integer w, Integer dw) {
        this.w = w;
        this.dw = dw;
    }

    /**
     * @return the w
     */
    public Integer getW() {
        return w;
    }

    /**
     * @return the dw
     */
    public Integer getDw() {
        return dw;
    }

    public static StoreCAP wdw(int w, int dw) {
        return new StoreCAP(w, dw);
    }

    public static StoreCAP w(int w) {
        return new StoreCAP(w, null);
    }

    public static StoreCAP dw(int dw) {
        return new StoreCAP(null, dw);
    }
}
