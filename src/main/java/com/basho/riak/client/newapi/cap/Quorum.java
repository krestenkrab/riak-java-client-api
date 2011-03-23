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
 * Enum for bucket level CAP properties.
 * 
 * r/w/dw/rw values for a bucket
 * 
 * ALL: every node
 * ONE: any one node
 * QUORUM: default, any nNval / 2 + 1 nodes
 * OTHER: any specified int value that is less than nVal nodes
 * 
 * TODO not happy with this at all, either it is an int or a symbolic value, hm
 * 
 * @author russell
 *
 */
public enum Quorum {
    ALL, ONE, QUORUM, OTHER;
    
    private int intVal;
    
    public int toInt() {
        return this.intVal;
    }
}
