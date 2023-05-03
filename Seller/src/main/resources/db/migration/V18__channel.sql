CREATE TABLE channel(
id character varying(255) UNIQUE NOT NULL,
name character varying(255),
url character varying(255));




INSERT INTO channel(id,name,url)VALUES('1','ebay','www.com');
INSERT INTO channel(id,name,url)VALUES('2','GAME',  'www.com');
INSERT INTO channel(id,name,url)VALUES('3','MUSIC MAGPIE','www.com');
INSERT INTO channel(id,name,url)VALUES('4','Grainger Games','www.com');
INSERT INTO channel(id,name,url)VALUES('5','BeatThebomb','www.com');
INSERT INTO channel(id,name,url)VALUES('6','Tesco',     'www.com');
INSERT INTO channel(id,name,url)VALUES('7','cash generator','www.com');
INSERT INTO channel(id,name,url)VALUES('8','accessories','www.com');
INSERT INTO channel(id,name,url)VALUES('9','Awesome Products','www.com');
INSERT INTO channel(id,name,url)VALUES('10','Cool Shop','www.com');
INSERT INTO channel(id,name,url)VALUES('11','Tigerali','www.com');
INSERT INTO channel(id,name,url)VALUES('12','Winningshades' ,'www.com');
INSERT INTO channel(id,name,url)VALUES('13','GamesXtension','www.com');


ALTER TABLE orders ADD COLUMN channel_id character varying(255);
