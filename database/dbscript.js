conn = new Mongo();
db = conn.getDB('tweets_db');
db.createCollection('sentiments');
db.createCollection('stories');
db.createCollection('entities');