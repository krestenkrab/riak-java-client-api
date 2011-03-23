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
 * Builds RiakObjects
 * 
 * @author russell
 * 
 */
public class RiakObjectBuilder implements Builder<RiakObject> {

    private final String bucket;
    private final String key;

    private RiakObjectBuilder(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public static RiakObjectBuilder newBuilder(String bucket, String key) {
        return new RiakObjectBuilder(bucket, key);
    }
    
    public static RiakObjectBuilder from(RiakObject o) {
        return new RiakObjectBuilder(o.getBucketName(), o.getKey());
    }

    public RiakObject build() {
        return null;
    }

}
