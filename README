# Riak Java Client API - request for comments

## What's wrong?
Accusations have been made against the current [riak-java-client](https://github.com/basho/riak-java-client). Certainly it leaks implementation details (Apache HttpClient, JSONArray, JSONObject, ByteString etc) into client code. And there are *3* different possible client interfaces:

* An Http style client
* A more OO client that uses the Http client
* A protocol buffers client

All of these leak their abstractions and force the user to make an upfront choice about transport/features and then code to that decision.

Some people don't like  Apache HttpClient, and that is fair enough, so it would be ideal if we didn't force it on those people. Better yet make it easy to create new implementations for the transport (using Netty or RestTemplate or carrier pigeon).

## What am I doing?
In an attempt to alliviate this I/we am/are attempting to design a simple API (and SPI (more later)) so that we can use the riak-java-client and not have to be concerened with the underlying transport, serialization format and other implementation trivia. Which is good, 'cos you *want* to use the protobufs client, m'kay?

## What can *you* do?
This repo is a request for comments. It is just a  starting point and I'll be pushing a lot over the next few days, but I'd really appreciate any riak Java client users (past, present or future) to look over this API and let me know your thoughts. It is, hopefully, the bare minimum API that is needed to speak to riak. Please contact me however you like[1] but ideally the [riak-users mailing list](http://lists.basho.com/mailman/listinfo/riak-users_lists.basho.com) so the discussion is public and archived.

## What next?
I'm going to start adapting the existing clients to this interface and duplicating the existing tests and then coding up the [riak fast track](http://wiki.basho.com/The-Riak-Fast-Track.html) ASAP, from that I hope to learn 2 things:

1. Will it work as an API
2. What is the simplest SPI for plugging in new transports

With the feedback garnered hopefully I can start working the client into the new API and the future will be rosy.

---

[1] alt. contact
* #riak on freenode
* the wiki for this repo
* pull requests
* [twitter](http://twitter.com/#!/russelldb)
* email (look in the pom)