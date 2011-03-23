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

import com.basho.riak.client.newapi.cap.StoreCAP;

/**
 * Store meta data for riak store operation.
 * 
 * @author russell
 * 
 */
public class StoreMeta {
    private final StoreCAP storeCAP;
    private final String contentType;

    private StoreMeta(Integer w, Integer dw, String contentType) {
        this.storeCAP = new StoreCAP(w, dw);
        this.contentType = contentType;
    }

    /**
     * @return
     * @see com.basho.riak.client.newapi.cap.StoreCAP#getW()
     */
    public Integer getW() {
        return storeCAP.getW();
    }

    public boolean hasW() {
        return storeCAP.getW() != null;
    }

    /**
     * @return
     * @see com.basho.riak.client.newapi.cap.StoreCAP#getDW()
     */
    public Integer getDw() {
        return storeCAP.getDW();
    }

    public boolean hasDW() {
        return storeCAP.getDW() != null;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    public boolean hasContentType() {
        return contentType != null;
    }
}
