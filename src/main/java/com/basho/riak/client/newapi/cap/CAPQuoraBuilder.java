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

import com.basho.riak.client.newapi.BucketProperties;
import com.basho.riak.client.newapi.Builder;

/**
 * Build CAPQuora.
 * 
 * @author russell
 * 
 */
public class CAPQuoraBuilder implements Builder<CAPQuora> {

    private Quorum r;
    private Quorum w;
    private Quorum dw;
    private Quorum rw;

    public CAPQuora build() {
        return new CAPQuora(r, w, dw, rw);
    }

    /**
     * Initialize a builder with values from an existing {@link CAPQuora}
     * 
     * @param quora
     * @return a Builder prepopulated with quora's values.
     */
    public static CAPQuoraBuilder from(CAPQuora quora) {
        return new CAPQuoraBuilder().r(quora.getR()).w(quora.getW()).rw(quora.getRW()).dw(quora.getDW());
    }

    /**
     * Initialize a builder with values from an existing {@link BucketProperties}
     * 
     * @param quora
     * @return a Builder prepopulated with properties' values.
     */
    public static CAPQuoraBuilder from(BucketProperties properties) {
        return new CAPQuoraBuilder().r(properties.getR()).w(properties.getW()).rw(properties.getRW()).dw(properties.getDW());
    }

    public CAPQuoraBuilder r(Quorum r) {
        this.r = r;
        return this;
    }

    public CAPQuoraBuilder w(Quorum w) {
        this.w = w;
        return this;
    }

    public CAPQuoraBuilder rw(Quorum rw) {
        this.rw = rw;
        return this;
    }

    public CAPQuoraBuilder dw(Quorum dw) {
        this.dw = dw;
        return this;
    }
}
