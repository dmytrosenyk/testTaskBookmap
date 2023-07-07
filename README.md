# testTaskBookmap
### Task from Bookmap
In order to complete the task, you will have to create an implementation of an order book.

#### testing:
For testing use site ```https://devtest1.hr.bookmap.com/task```

#### Requirements:
Each line in the file can be one of the following:

Updates to the limit order book in the following format:
* u,```price```,```size```,bid - set bid size at ```price``` to ```size```
* u,```price```,```size```,ask - set ask size at ```price``` to ```size```
Queries in the following format:
* q,best_bid - print best bid price and size
* q,best_ask - print best ask price and size
* q,size,```price``` - print size at specified price (bid, ask or spread).
And market orders in the following format:
* o,buy,```size``` - removes ```size``` shares out of asks, most cheap ones.
* o,sell,```size``` - removes ```size``` shares out of bids, most expensive ones
In case of a buy order this is similar to going to a market (assuming that you want to buy ```size``` similar items there, and that all instances have identical quality, so price is the only factor) - you buy ```size``` units at the cheapest price available.

Queries, market orders, and limit order book updates are in arbitrary sequence. Each line in the file is either one of the three and ends with a UNIX newline character - \n.
