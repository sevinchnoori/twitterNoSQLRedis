# TwitterNoSQLRedis

## Intro

When Twitter first started out, its engineers used MySQL as a backend relational database. 
As the service grew in popularity, they were forced to abandon MySQL in favor of Redis, a 
NoSQL key-value store. 

There are two key operations of twitter that my code supports:

a) Users post tweets. We are ignoring hashtags. 

b) Users retrieve all of the tweets posted by all users followed by that user. 
This set of tweets – which the user sees when he or she opens up the twitter app 
on a smart phone – is known as the user’s home timeline.

## Database Initialization

TWEET – The tweets posted by users

tweet_id INT (PK)

user_id INT (FK)

tweet_ts DATETIME

tweet_text VARCHAR(140)



FOLLOWS – Who follows whom. The user “user_id” follows the user “follows_id”

user_id INT (FK)

follows_id INT (FK)



follows.csv and tweets.csv loaded into the database (too large to include in this repository)

## How It Works

The program reads pre-generated tweets from the file tweets.csv. Data from follows.csv was 
loaded into the follows table by using the 'data import utilities' feature from MySQL Workbench.

It also auto-assigns tweet_ids and timestamps as the tweet is loaded into the database

After all 1 million tweets have been loaded into the database, the program repeatedly picks a random 
user and returns that user’s home timeline. We define the home timeline as the 10 most recent tweets 
posted by users followed by our randomly selected user. For example, if user A follows X, Y, and Z, 
then A’s home timeline consists of the 10 most recent tweets posted by X, Y, or Z. This process 
simulates users opening the twitter app on their smartphone and refreshing the home timeline to see
recent posts. 
