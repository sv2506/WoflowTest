# WoflowTest

I use a BFS method in this approach. Using the ID given as input, 
the code makes the GET request, and using some JSON parsing it 
retrieves all the "child_node_ids" for the parent.

It then iterates over the child nodes, makes sure that we have not 
visited the node already(To avoid an infinte loop) by checking 
against the HashMap that is defined which stores all unique nodes 
and their frequenct.

If node was not visited, then add it to the queue of nodes to visit. 
Simultaneously, update the frequency in the map.

The while loop will exit when we end up on a node that does not have any 
children and this causes the queue to become empty.

NOTE: In the initial commits, I was only able to add the logic but not the result.
My JSON parser logic got a bit messed up since some of the dependencies were missing in my IDE.

In my final commit, I was able to fix the JSON parser. 
