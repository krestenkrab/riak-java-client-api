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
 * Server information for a connected riak node.
 * 
 * @see {@link RiakClient#getServerInfo()}
 * 
 * @author russell
 * 
 */
public class RiakServerInfo {
    private final String node;
    private final String version;

    public RiakServerInfo(String node, String version) {
        this.node = node;
        this.version = version;
    }

    /**
     * @return the node
     */
    public String getNode() {
        return node;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }
}
