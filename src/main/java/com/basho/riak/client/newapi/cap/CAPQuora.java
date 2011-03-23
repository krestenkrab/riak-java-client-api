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
package com.basho.riak.client.newapi.cap;

/**
 * The set of CAP properties for a bucket.
 * 
 * Immutable.
 * 
 * @author russell
 * 
 */
public class CAPQuora {

    private final Quorum r;
    private final Quorum w;
    private final Quorum dw;
    private final Quorum rw;

    public CAPQuora(Quorum r, Quorum w, Quorum dw, Quorum rw) {
        this.r = r;
        this.w = w;
        this.dw = dw;
        this.rw = rw;
    }

    /**
     * @return the r
     */
    public Quorum getR() {
        return r;
    }

    /**
     * @return the w
     */
    public Quorum getW() {
        return w;
    }

    /**
     * @return the dw
     */
    public Quorum getDW() {
        return dw;
    }

    /**
     * @return the rw
     */
    public Quorum getRW() {
        return rw;
    }

    public static CAPQuoraBuilder from(CAPQuora capQuora) {
        return CAPQuoraBuilder.from(capQuora);
    }
    
    public CAPQuoraBuilder fromMe() {
        return CAPQuora.from(this);
    }
}
