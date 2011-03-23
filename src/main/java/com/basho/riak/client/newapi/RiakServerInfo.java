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

import java.util.Map;

/**
 * Server information for a connected riak node.
 * 
 * @see {@link RiakClient#getServerInfo()}
 * 
 *      Immutable.
 * 
 * @author russell
 * 
 */
public class RiakServerInfo {
    public static final String NODE_KEY = "node";
    public static final String VERSION_KEY = "server_version";

    private final String node;
    private final String version;

    /**
     * 
     * @param node the name of the node
     * @param version the riak version of node
     */
    public RiakServerInfo(String node, String version) {
        this.node = node;
        this.version = version;
    }

    /**
     * @return the node name
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

    /**
     * Creates a {@link RiakServerInfo} from a Map.
     * 
     * @param serverInfo
     *            a Map with the keys {@link RiakServerInfo#NODE_KEY} and
     *            {@link RiakServerInfo#VERSION_KEY}
     * @return A RiakServerInfo populated from the map.
     * @throws IllegalArgumentException
     *             if map is null or does not contain NODE_KEY and VERSION_KEY
     */
    public static RiakServerInfo fromMap(Map<String, String> serverInfo) {
        if (serverInfo == null || !serverInfo.containsKey(NODE_KEY) || !serverInfo.containsKey(VERSION_KEY)) {
            throw new IllegalArgumentException("serverInfo must contain the keys " + NODE_KEY + " and " + VERSION_KEY);
        }
        return new RiakServerInfo(serverInfo.get(NODE_KEY), serverInfo.get(VERSION_KEY));
    }
}
