This is a performance measurement project for concurrent data structures.  The assignment instructions are provided below.

Assignment 2: 
This is mainly an exercise in performance measurement. Each of the following steps has many possible variations; you are free to choose any of them.
Think of some kind of application in which a set of threads all rely on a shared collection of possibly-prioritized data; sometimes read-only, sometimes modifying the data. For example, a game-server with game-state as the collection, or a campus course scheduling system. Write a stripped-down version of this in which all the threads just emulate clients, and further strips out nearly everything except the reading and writing (while still somehow using results).
Write one solution using a data structure and/or locking scheme of your own devising (most likely a variant of some known technique). Write another to primarily use standard JDK components.
Compare the throughput of your program across at least two different loads on each of at least two different platforms. Use JMH unless you have an approved reason not to. 
Plot your results as a set of graphs and place on a web page.
