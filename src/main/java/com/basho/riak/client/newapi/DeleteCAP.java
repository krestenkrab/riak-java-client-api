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
 * Per request tunable CAP settings for a delete operation.
 * 
 * @author russell
 * 
 */
public final class DeleteCAP {
    private final Integer rw;

    public DeleteCAP(int rw) {
        this.rw = rw;
    }

    /**
     * @return the rw
     */
    public int getRw() {
        return rw;
    }
    
    public static DeleteCAP rw(int rw) {
        return new DeleteCAP(rw);
    }
}
